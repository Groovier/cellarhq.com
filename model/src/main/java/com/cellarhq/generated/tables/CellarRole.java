/**
 * This class is generated by jOOQ
 */
package com.cellarhq.generated.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.1" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CellarRole extends org.jooq.impl.TableImpl<com.cellarhq.generated.tables.records.CellarRoleRecord> {

	private static final long serialVersionUID = -1671733264;

	/**
	 * The singleton instance of <code>public.cellar_role</code>
	 */
	public static final com.cellarhq.generated.tables.CellarRole CELLAR_ROLE = new com.cellarhq.generated.tables.CellarRole();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<com.cellarhq.generated.tables.records.CellarRoleRecord> getRecordType() {
		return com.cellarhq.generated.tables.records.CellarRoleRecord.class;
	}

	/**
	 * The column <code>public.cellar_role.id</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.CellarRoleRecord, java.lang.Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>public.cellar_role.version</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.CellarRoleRecord, java.lang.Integer> VERSION = createField("version", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>public.cellar_role.cellar_id</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.CellarRoleRecord, java.lang.Long> CELLAR_ID = createField("cellar_id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

	/**
	 * The column <code>public.cellar_role.role</code>.
	 */
	public final org.jooq.TableField<com.cellarhq.generated.tables.records.CellarRoleRecord, java.lang.String> ROLE = createField("role", org.jooq.impl.SQLDataType.VARCHAR.length(20).nullable(false), this, "");

	/**
	 * Create a <code>public.cellar_role</code> table reference
	 */
	public CellarRole() {
		this("cellar_role", null);
	}

	/**
	 * Create an aliased <code>public.cellar_role</code> table reference
	 */
	public CellarRole(java.lang.String alias) {
		this(alias, com.cellarhq.generated.tables.CellarRole.CELLAR_ROLE);
	}

	private CellarRole(java.lang.String alias, org.jooq.Table<com.cellarhq.generated.tables.records.CellarRoleRecord> aliased) {
		this(alias, aliased, null);
	}

	private CellarRole(java.lang.String alias, org.jooq.Table<com.cellarhq.generated.tables.records.CellarRoleRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, com.cellarhq.generated.Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<com.cellarhq.generated.tables.records.CellarRoleRecord, java.lang.Long> getIdentity() {
		return com.cellarhq.generated.Keys.IDENTITY_CELLAR_ROLE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<com.cellarhq.generated.tables.records.CellarRoleRecord> getPrimaryKey() {
		return com.cellarhq.generated.Keys.PK_CELLAR_ROLE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<com.cellarhq.generated.tables.records.CellarRoleRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<com.cellarhq.generated.tables.records.CellarRoleRecord>>asList(com.cellarhq.generated.Keys.PK_CELLAR_ROLE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<com.cellarhq.generated.tables.records.CellarRoleRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<com.cellarhq.generated.tables.records.CellarRoleRecord, ?>>asList(com.cellarhq.generated.Keys.CELLAR_ROLE__FK_CELLAR_ROLE_CELLAR_ID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.TableField<com.cellarhq.generated.tables.records.CellarRoleRecord, java.lang.Integer> getRecordVersion() {
		return com.cellarhq.generated.tables.CellarRole.CELLAR_ROLE.VERSION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public com.cellarhq.generated.tables.CellarRole as(java.lang.String alias) {
		return new com.cellarhq.generated.tables.CellarRole(alias, this);
	}

	/**
	 * Rename this table
	 */
	public com.cellarhq.generated.tables.CellarRole rename(java.lang.String name) {
		return new com.cellarhq.generated.tables.CellarRole(name, null);
	}
}