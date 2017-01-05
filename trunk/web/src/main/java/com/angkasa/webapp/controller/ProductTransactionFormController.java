package com.angkasa.webapp.controller;

import com.angkasa.model.Product;
import com.angkasa.service.ProductManager;
import com.angkasa.util.PropsUtil;
import com.angkasa.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import com.angkasa.service.ProductTransactionManager;
import com.angkasa.model.ProductTransaction;
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
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/productTransactionform*")
public class ProductTransactionFormController extends BaseFormController {
    private ProductTransactionManager productTransactionManager = null;

    @Autowired
    public void setProductTransactionManager(ProductTransactionManager productTransactionManager) {
        this.productTransactionManager = productTransactionManager;
    }

    @Autowired
    ProductManager productManager;

    @Autowired
    PropsUtil propsUtil;

    @ModelAttribute("transactionTypeList")
    public Map<String, String> getTransactionTypeList() {
        return propsUtil.getTransactionTypeList();
    }

    public ProductTransactionFormController() {
        setCancelView("redirect:productTransactions");
        setSuccessView("redirect:productTransactions");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected ProductTransaction showForm(HttpServletRequest request)
            throws Exception {
        String id = request.getParameter("id");
        String productId = request.getParameter("productId");
        String transNo = request.getParameter("transNo");

        if (!StringUtils.isBlank(id)) {
            return productTransactionManager.get(new Long(id));
        }

        if (StringUtils.isNotBlank(productId)) {
            ProductTransaction productTransaction = new ProductTransaction();
            productTransaction.setProduct(productManager.get(new Long(productId)));
            return productTransaction;
        }

        return new ProductTransaction();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(ProductTransaction productTransaction, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
            throws Exception {

        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(productTransaction, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "productTransactionform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (productTransaction.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            productTransactionManager.remove(productTransaction.getId());
            saveMessage(request, getText("productTransaction.deleted", locale));
        } else {
            if (productTransaction.getProduct() != null && productTransaction.getProduct().getId() != null) {
                setSuccessView("redirect:/productform?id=" + productTransaction.getProduct().getId());
                setCancelView("redirect:/productform?id=" + productTransaction.getProduct().getId());
            } else {
                saveError(request, "Please open this page from a Product Details page.");
                return "redirect:/home";
            }

            productTransactionManager.save(productTransaction);
            String key = (isNew) ? "productTransaction.added" : "productTransaction.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:/productTransactionform?id=" + productTransaction.getId();
            }
        }

        return success;
    }
}
