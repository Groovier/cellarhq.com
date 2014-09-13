/**
 * This class is generated by jOOQ
 */
package com.cellarhq.generated.tables.pojos;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.1" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
@javax.persistence.Entity
@javax.persistence.Table(name = "organization", schema = "public")
public class Organization implements java.io.Serializable {

	private static final long serialVersionUID = -1979069730;

	private java.lang.Long     id;
	private java.lang.Integer  version;
	private java.lang.Long     photoId;
	private java.lang.String   type;
	private java.lang.String   slug;
	private java.lang.String   name;
	private java.lang.String   description;
	private java.lang.Short    established;
	private java.lang.String   phone;
	private java.lang.String   website;
	private java.lang.String   address;
	private java.lang.String   address2;
	private java.lang.String   locality;
	private java.lang.String   postalCode;
	private java.lang.String   country;
	private java.lang.Boolean  searchable;
	private java.lang.String   breweryDbId;
	private java.sql.Timestamp breweryDbLastUpdated;
	private java.lang.Boolean  locked;
	private java.lang.Boolean  needsModeration;
	private java.sql.Timestamp createdDate;
	private java.sql.Timestamp modifiedDate;
	private java.lang.Object   data;
	private java.lang.String   region;

	public Organization() {}

	public Organization(
		java.lang.Long     id,
		java.lang.Integer  version,
		java.lang.Long     photoId,
		java.lang.String   type,
		java.lang.String   slug,
		java.lang.String   name,
		java.lang.String   description,
		java.lang.Short    established,
		java.lang.String   phone,
		java.lang.String   website,
		java.lang.String   address,
		java.lang.String   address2,
		java.lang.String   locality,
		java.lang.String   postalCode,
		java.lang.String   country,
		java.lang.Boolean  searchable,
		java.lang.String   breweryDbId,
		java.sql.Timestamp breweryDbLastUpdated,
		java.lang.Boolean  locked,
		java.lang.Boolean  needsModeration,
		java.sql.Timestamp createdDate,
		java.sql.Timestamp modifiedDate,
		java.lang.Object   data,
		java.lang.String   region
	) {
		this.id = id;
		this.version = version;
		this.photoId = photoId;
		this.type = type;
		this.slug = slug;
		this.name = name;
		this.description = description;
		this.established = established;
		this.phone = phone;
		this.website = website;
		this.address = address;
		this.address2 = address2;
		this.locality = locality;
		this.postalCode = postalCode;
		this.country = country;
		this.searchable = searchable;
		this.breweryDbId = breweryDbId;
		this.breweryDbLastUpdated = breweryDbLastUpdated;
		this.locked = locked;
		this.needsModeration = needsModeration;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.data = data;
		this.region = region;
	}

	@javax.persistence.Id
	@javax.persistence.Column(name = "id", unique = true, nullable = false, precision = 64)
	public java.lang.Long getId() {
		return this.id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	@javax.persistence.Column(name = "version", nullable = false, precision = 32)
	public java.lang.Integer getVersion() {
		return this.version;
	}

	public void setVersion(java.lang.Integer version) {
		this.version = version;
	}

	@javax.persistence.Column(name = "photo_id", precision = 64)
	public java.lang.Long getPhotoId() {
		return this.photoId;
	}

	public void setPhotoId(java.lang.Long photoId) {
		this.photoId = photoId;
	}

	@javax.persistence.Column(name = "type", nullable = false, length = 16)
	public java.lang.String getType() {
		return this.type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	@javax.persistence.Column(name = "slug", unique = true, nullable = false, length = 100)
	public java.lang.String getSlug() {
		return this.slug;
	}

	public void setSlug(java.lang.String slug) {
		this.slug = slug;
	}

	@javax.persistence.Column(name = "name", nullable = false, length = 100)
	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	@javax.persistence.Column(name = "description")
	public java.lang.String getDescription() {
		return this.description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	@javax.persistence.Column(name = "established", precision = 16)
	public java.lang.Short getEstablished() {
		return this.established;
	}

	public void setEstablished(java.lang.Short established) {
		this.established = established;
	}

	@javax.persistence.Column(name = "phone", length = 30)
	public java.lang.String getPhone() {
		return this.phone;
	}

	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}

	@javax.persistence.Column(name = "website", length = 100)
	public java.lang.String getWebsite() {
		return this.website;
	}

	public void setWebsite(java.lang.String website) {
		this.website = website;
	}

	@javax.persistence.Column(name = "address", length = 100)
	public java.lang.String getAddress() {
		return this.address;
	}

	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	@javax.persistence.Column(name = "address2", length = 100)
	public java.lang.String getAddress2() {
		return this.address2;
	}

	public void setAddress2(java.lang.String address2) {
		this.address2 = address2;
	}

	@javax.persistence.Column(name = "locality", length = 100)
	public java.lang.String getLocality() {
		return this.locality;
	}

	public void setLocality(java.lang.String locality) {
		this.locality = locality;
	}

	@javax.persistence.Column(name = "postal_code", length = 20)
	public java.lang.String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(java.lang.String postalCode) {
		this.postalCode = postalCode;
	}

	@javax.persistence.Column(name = "country", length = 100)
	public java.lang.String getCountry() {
		return this.country;
	}

	public void setCountry(java.lang.String country) {
		this.country = country;
	}

	@javax.persistence.Column(name = "searchable", nullable = false)
	public java.lang.Boolean getSearchable() {
		return this.searchable;
	}

	public void setSearchable(java.lang.Boolean searchable) {
		this.searchable = searchable;
	}

	@javax.persistence.Column(name = "brewery_db_id", length = 64)
	public java.lang.String getBreweryDbId() {
		return this.breweryDbId;
	}

	public void setBreweryDbId(java.lang.String breweryDbId) {
		this.breweryDbId = breweryDbId;
	}

	@javax.persistence.Column(name = "brewery_db_last_updated")
	public java.sql.Timestamp getBreweryDbLastUpdated() {
		return this.breweryDbLastUpdated;
	}

	public void setBreweryDbLastUpdated(java.sql.Timestamp breweryDbLastUpdated) {
		this.breweryDbLastUpdated = breweryDbLastUpdated;
	}

	@javax.persistence.Column(name = "locked")
	public java.lang.Boolean getLocked() {
		return this.locked;
	}

	public void setLocked(java.lang.Boolean locked) {
		this.locked = locked;
	}

	@javax.persistence.Column(name = "needs_moderation", nullable = false)
	public java.lang.Boolean getNeedsModeration() {
		return this.needsModeration;
	}

	public void setNeedsModeration(java.lang.Boolean needsModeration) {
		this.needsModeration = needsModeration;
	}

	@javax.persistence.Column(name = "created_date", nullable = false)
	public java.sql.Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(java.sql.Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	@javax.persistence.Column(name = "modified_date", nullable = false)
	public java.sql.Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(java.sql.Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@javax.persistence.Column(name = "data")
	public java.lang.Object getData() {
		return this.data;
	}

	public void setData(java.lang.Object data) {
		this.data = data;
	}

	@javax.persistence.Column(name = "region", length = 100)
	public java.lang.String getRegion() {
		return this.region;
	}

	public void setRegion(java.lang.String region) {
		this.region = region;
	}
}