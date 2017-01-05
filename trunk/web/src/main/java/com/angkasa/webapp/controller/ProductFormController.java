package com.angkasa.webapp.controller;

import com.angkasa.model.Bank;
import com.angkasa.model.ProductTransaction;
import com.angkasa.service.BankManager;
import com.angkasa.service.ProductTransactionManager;
import com.angkasa.util.PropsUtil;
import org.apache.commons.lang.StringUtils;
import com.angkasa.service.ProductManager;
import com.angkasa.model.Product;
import com.angkasa.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/productform*")
public class ProductFormController extends BaseFormController {
    private ProductManager productManager = null;

    @Autowired
    PropsUtil propsUtil;

    @Autowired
    ProductTransactionManager productTransactionManager;

    @Autowired
    BankManager bankManager;

    @Autowired
    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }

    @ModelAttribute("productPeriodList")
    public Map<String, String> getProductPeriodList() {
        return propsUtil.getProductPeriodList();
    }

    @ModelAttribute("transactionTypeList")
    public Map<String, String> getTransactionTypeList() {
        return propsUtil.getTransactionTypeList();
    }

    @ModelAttribute("banksList")
    public Map<Long, String> getBankList() {
        List<Bank> bankList = bankManager.getAll();
        Map<Long, String> bankMap = new HashMap<>();
        bankMap.put(null, "N/A");
        for (Bank bank : bankList) {
            bankMap.put(bank.getId(), bank.getName() + " (" + bank.getCode() + ")");
        }
        return bankMap;
    }

    public ProductFormController() {
        setCancelView("redirect:products");
        setSuccessView("redirect:products");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Product showForm(HttpServletRequest request)
            throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            Product product = productManager.get(new Long(id));
            List<ProductTransaction> productTransactionList = productTransactionManager.getByProductId(product.getId());
            product.setProductTransactions(productTransactionList);
            return product;
        }

        return new Product();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Product product, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(product, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "productform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (product.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            productManager.remove(product.getId());
            saveMessage(request, getText("product.deleted", locale));

        } else {
            if (isNew) {
                product = productManager.saveWithTransactions(product);
            } else {
                product = productManager.save(product);
            }

            String key = (isNew) ? "product.added" : "product.updated";
            saveMessage(request, getText(key, locale));

            if (isNew) {
                success = "redirect:/productform?id=" + product.getId() + "&tab=transactionDetails";
            } else {
                success = "redirect:/productform?id=" + product.getId();
            }

        }

        return success;
    }
}
