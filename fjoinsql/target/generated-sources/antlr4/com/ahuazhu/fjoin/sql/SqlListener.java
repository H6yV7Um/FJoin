// Generated from com/ahuazhu/fjoin/sql/Sql.g4 by ANTLR 4.5.3
package com.ahuazhu.fjoin.sql;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SqlParser}.
 */
public interface SqlListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SqlParser#sql}.
	 * @param ctx the parse tree
	 */
	void enterSql(SqlParser.SqlContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#sql}.
	 * @param ctx the parse tree
	 */
	void exitSql(SqlParser.SqlContext ctx);
}