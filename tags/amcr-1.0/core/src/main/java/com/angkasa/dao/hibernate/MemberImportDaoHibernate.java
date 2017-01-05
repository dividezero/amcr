package com.angkasa.dao.hibernate;

import com.angkasa.Constants;
import com.angkasa.dao.CoopDao;
import com.angkasa.dao.MemberDao;
import com.angkasa.model.*;
import com.angkasa.dao.MemberImportDao;
import com.angkasa.service.DocumentManager;
import com.angkasa.util.PropsUtil;
import com.angkasa.util.XlsxParserUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;

@Repository("memberImportDao")
public class MemberImportDaoHibernate extends GenericDaoHibernate<MemberImport, Long> implements MemberImportDao {

    @Autowired
    MemberDao memberDao;

    @Autowired
    CoopDao coopDao;

    @Autowired
    DocumentManager documentManager;

    @Autowired
    PropsUtil propsUtil;

    public MemberImportDaoHibernate() {
        super(MemberImport.class);
    }

    @Override
    public MemberImport saveWithFile(MemberImport memberImport) throws Exception{
        if(memberImport.getFile() != null && StringUtils.isNotBlank(memberImport.getName())){

            Document document = null;
            try {
                document = documentManager.add(DocumentManager.MODULE_EMPLOYER_IMPORT, memberImport.getName(), memberImport.getName(),
                        DocumentManager.MODULE_EMPLOYER_IMPORT, "application/xlsx", "", Base64.encode(memberImport.getFile()));
            } catch (Exception e){
                e.printStackTrace();
            }
            if(document != null){

                byte[] byteArray = documentManager.getFileByteArray(document.getId());
                String fileName = propsUtil.getDocumentRootPath() + Constants.SYSTEM_SLASH + memberImport.getName() + "-memtest.xlsx";
                FileUtils.writeByteArrayToFile(new File(fileName), byteArray);

                int noOfColumns = XlsxParserUtil.getNumberOfColumns(new File(fileName));

                List<Member> memberList = XlsxParserUtil.parseMemberXlsx(new File(fileName));

                if(memberImport.getCoopId() != null){
                    Coop coop = coopDao.get(memberImport.getCoopId());
                    for (Member member : memberList){
                        member.setImportCoopCode(coop.getCoopCode());
                    }
                }

                List<Member> memberListAfter = memberDao.validateMemberImportList(memberList);

                // Find import result status
                boolean hasFailed = false;
                boolean hasSuccess = false;
                for(Member mem : memberListAfter){
                    if(StringUtils.isBlank(mem.getImportStatus())){
                        mem.setImportStatus(Constants.IMPORT_STATUS_SUCCESS);
                    }
                    if(mem.getImportStatus().equals(Constants.IMPORT_STATUS_SUCCESS)){
                        hasSuccess = true;
                    }
                    if(mem.getImportStatus().equals(Constants.IMPORT_STATUS_FAILED)){
                        hasFailed = true;
                    }
                }
                if(hasFailed && hasSuccess){
                    memberImport.setStatus(MemberImport.STATUS_PARTIAL_SUCCESS);
                } else if (hasFailed){
                    memberImport.setStatus(MemberImport.STATUS_FAILED);
                } else {
                    memberImport.setStatus(MemberImport.STATUS_SUCCESS);
                }

                memberImport.setMemberList(memberListAfter);
                memberImport.setDocumentId(document.getId());

                memberImport = this.save(memberImport);
            }
        }

        return memberImport;
    }

}
