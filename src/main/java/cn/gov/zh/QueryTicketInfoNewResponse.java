
package cn.gov.zh;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResultCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Describe" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PuserId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UserAccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EmailAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="UserFlag" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="MobileNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BandingAccountList" type="{http://zh.gov.cn}ArrayOfBandingAccount" minOccurs="0"/>
 *         &lt;element name="User" type="{http://zh.gov.cn}User" minOccurs="0"/>
 *         &lt;element name="Unit" type="{http://zh.gov.cn}Unit" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "resultCode",
    "describe",
    "puserId",
    "userAccount",
    "emailAddress",
    "accountType",
    "userFlag",
    "mobileNo",
    "bandingAccountList",
    "user",
    "unit"
})
@XmlRootElement(name = "QueryTicketInfoNewResponse")
public class QueryTicketInfoNewResponse {

    @XmlElement(name = "ResultCode")
    protected int resultCode;
    @XmlElement(name = "Describe", required = true)
    protected String describe;
    @XmlElement(name = "PuserId")
    protected String puserId;
    @XmlElement(name = "UserAccount")
    protected String userAccount;
    @XmlElement(name = "EmailAddress")
    protected String emailAddress;
    @XmlElement(name = "AccountType")
    protected Integer accountType;
    @XmlElement(name = "UserFlag")
    protected Integer userFlag;
    @XmlElement(name = "MobileNo")
    protected String mobileNo;
    @XmlElement(name = "BandingAccountList")
    protected ArrayOfBandingAccount bandingAccountList;
    @XmlElement(name = "User")
    protected User user;
    @XmlElement(name = "Unit")
    protected Unit unit;

    /**
     * Gets the value of the resultCode property.
     * 
     */
    public int getResultCode() {
        return resultCode;
    }

    /**
     * Sets the value of the resultCode property.
     * 
     */
    public void setResultCode(int value) {
        this.resultCode = value;
    }

    /**
     * Gets the value of the describe property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * Sets the value of the describe property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescribe(String value) {
        this.describe = value;
    }

    /**
     * Gets the value of the puserId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPuserId() {
        return puserId;
    }

    /**
     * Sets the value of the puserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPuserId(String value) {
        this.puserId = value;
    }

    /**
     * Gets the value of the userAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserAccount() {
        return userAccount;
    }

    /**
     * Sets the value of the userAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserAccount(String value) {
        this.userAccount = value;
    }

    /**
     * Gets the value of the emailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the value of the emailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailAddress(String value) {
        this.emailAddress = value;
    }

    /**
     * Gets the value of the accountType property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAccountType() {
        return accountType;
    }

    /**
     * Sets the value of the accountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAccountType(Integer value) {
        this.accountType = value;
    }

    /**
     * Gets the value of the userFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUserFlag() {
        return userFlag;
    }

    /**
     * Sets the value of the userFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUserFlag(Integer value) {
        this.userFlag = value;
    }

    /**
     * Gets the value of the mobileNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * Sets the value of the mobileNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobileNo(String value) {
        this.mobileNo = value;
    }

    /**
     * Gets the value of the bandingAccountList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfBandingAccount }
     *     
     */
    public ArrayOfBandingAccount getBandingAccountList() {
        return bandingAccountList;
    }

    /**
     * Sets the value of the bandingAccountList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfBandingAccount }
     *     
     */
    public void setBandingAccountList(ArrayOfBandingAccount value) {
        this.bandingAccountList = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setUser(User value) {
        this.user = value;
    }

    /**
     * Gets the value of the unit property.
     * 
     * @return
     *     possible object is
     *     {@link Unit }
     *     
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * Sets the value of the unit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Unit }
     *     
     */
    public void setUnit(Unit value) {
        this.unit = value;
    }

}
