
package cn.gov.zh;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for User complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="User">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CertNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ContactAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PlaceOfDomicile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sex" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="AccountNature" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Profession" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Trade" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Education" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Birthdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CERTID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Nation" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "User", propOrder = {
    "userName",
    "certNo",
    "contactAddress",
    "placeOfDomicile",
    "sex",
    "accountNature",
    "profession",
    "trade",
    "education",
    "birthdate",
    "certid",
    "nation"
})
public class User {

    @XmlElement(name = "UserName")
    protected String userName;
    @XmlElement(name = "CertNo")
    protected String certNo;
    @XmlElement(name = "ContactAddress")
    protected String contactAddress;
    @XmlElement(name = "PlaceOfDomicile")
    protected String placeOfDomicile;
    protected Integer sex;
    @XmlElement(name = "AccountNature")
    protected Integer accountNature;
    @XmlElement(name = "Profession")
    protected Integer profession;
    @XmlElement(name = "Trade")
    protected Integer trade;
    @XmlElement(name = "Education")
    protected Integer education;
    @XmlElement(name = "Birthdate")
    protected String birthdate;
    @XmlElement(name = "CERTID")
    protected Integer certid;
    @XmlElement(name = "Nation")
    protected Integer nation;

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * Gets the value of the certNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertNo() {
        return certNo;
    }

    /**
     * Sets the value of the certNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertNo(String value) {
        this.certNo = value;
    }

    /**
     * Gets the value of the contactAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactAddress() {
        return contactAddress;
    }

    /**
     * Sets the value of the contactAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactAddress(String value) {
        this.contactAddress = value;
    }

    /**
     * Gets the value of the placeOfDomicile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlaceOfDomicile() {
        return placeOfDomicile;
    }

    /**
     * Sets the value of the placeOfDomicile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlaceOfDomicile(String value) {
        this.placeOfDomicile = value;
    }

    /**
     * Gets the value of the sex property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * Sets the value of the sex property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSex(Integer value) {
        this.sex = value;
    }

    /**
     * Gets the value of the accountNature property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAccountNature() {
        return accountNature;
    }

    /**
     * Sets the value of the accountNature property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAccountNature(Integer value) {
        this.accountNature = value;
    }

    /**
     * Gets the value of the profession property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProfession() {
        return profession;
    }

    /**
     * Sets the value of the profession property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProfession(Integer value) {
        this.profession = value;
    }

    /**
     * Gets the value of the trade property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTrade() {
        return trade;
    }

    /**
     * Sets the value of the trade property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTrade(Integer value) {
        this.trade = value;
    }

    /**
     * Gets the value of the education property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEducation() {
        return education;
    }

    /**
     * Sets the value of the education property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEducation(Integer value) {
        this.education = value;
    }

    /**
     * Gets the value of the birthdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthdate() {
        return birthdate;
    }

    /**
     * Sets the value of the birthdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthdate(String value) {
        this.birthdate = value;
    }

    /**
     * Gets the value of the certid property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCERTID() {
        return certid;
    }

    /**
     * Sets the value of the certid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCERTID(Integer value) {
        this.certid = value;
    }

    /**
     * Gets the value of the nation property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNation() {
        return nation;
    }

    /**
     * Sets the value of the nation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNation(Integer value) {
        this.nation = value;
    }

}
