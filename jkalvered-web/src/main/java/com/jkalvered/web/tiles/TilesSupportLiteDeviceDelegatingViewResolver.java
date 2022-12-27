package com.jkalvered.web.tiles;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.mobile.device.site.SitePreferenceUtils;
import org.springframework.mobile.device.util.ResolverUtils;
import org.springframework.mobile.device.view.LiteDeviceDelegatingViewResolver;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ViewResolver;

/**
 * A class that resolves view names base detecting mobile (or non-mobile)
 * devices. This is exactly the same as {@link LiteDeviceDelegatingViewResolver}
 * but on top of changing the view name a configurable Attribute is added to the
 * request. Useful, for example, to change the JSP name based on device in Tiles
 *
 * @author Andres
 *
 */
public class TilesSupportLiteDeviceDelegatingViewResolver extends
        LiteDeviceDelegatingViewResolver {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
    //the name of the attribute to put in scope
    protected String attributeName = "mDeviceType";
    //the value of the attribute #attributeName for Mobile
    protected String mobileValue = ".m";
    protected String tabletValue = ".t";
    protected String normalValue = "";

    public TilesSupportLiteDeviceDelegatingViewResolver(ViewResolver delegate) {
        super(delegate);
        logger.info("je suis construit delegate");
    }

    @Override
    protected String getDeviceViewNameInternal(String viewName) {
        RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
        Assert.isInstanceOf(ServletRequestAttributes.class, attrs);
        HttpServletRequest request = ((ServletRequestAttributes) attrs).getRequest();
        Device device = DeviceUtils.getCurrentDevice(request);
        SitePreference sitePreference = SitePreferenceUtils.getCurrentSitePreference(request);
        String resolvedViewName = viewName;
        String attributeValue = normalValue;
        if (ResolverUtils.isNormal(device, sitePreference)) {
            resolvedViewName = getNormalPrefix() + viewName + getNormalSuffix();
            attributeValue = normalValue;
        } else if (ResolverUtils.isMobile(device, sitePreference)) {
            resolvedViewName = getMobilePrefix() + viewName + getMobileSuffix();
            attributeValue = mobileValue;
        } else if (ResolverUtils.isTablet(device, sitePreference)) {
            resolvedViewName = getTabletPrefix() + viewName + getTabletSuffix();
            attributeValue = tabletValue;
        }
        request.setAttribute(attributeName, attributeValue);
        logger.info("attributeValue:" + attributeValue);
        logger.info("resolvedViewName:" + resolvedViewName);
        return resolvedViewName;
    }

    public String getAttributeName() {
        return attributeName;
    }

    /**
     * Specifies the name of the attribute to be put into the request scope.
     * Defaults to 'm.deviceType'
     *
     * @param attributeName
     */
    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getMobileValue() {
        return mobileValue;
    }

    /**
     * Specifies the value to be set to the AttributeName when the detected
     * device is Mobile
     *
     * @param mobileValue
     */
    public void setMobileValue(String mobileValue) {
        this.mobileValue = mobileValue;
    }

    public String getTabletValue() {
        return tabletValue;
    }

    /**
     * Specifies the value to be set to the AttributeName when the detected
     * device is Tablet
     *
     * @param tabletValue
     */
    public void setTabletValue(String tabletValue) {
        this.tabletValue = tabletValue;
    }

    public String getNormalValue() {
        return normalValue;
    }

    /**
     * Specifies the value to be set to the AttributeName when the detected
     * device is Normal (i.e. not-mobile and not-tablet)
     *
     * @param normalValue
     */
    public void setNormalValue(String normalValue) {
        this.normalValue = normalValue;
    }
}
