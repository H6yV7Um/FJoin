package com.ahuazhu.fjoin.sql;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * Created by zhuzhengwen on 2017/6/29.
 */
public class SQLVisitor extends SqlBaseListener {


    @Override
    public void enterSql(SqlParser.SqlContext ctx) {
        super.enterSql(ctx);
        System.out.println("SQL: " + ctx.getText());
    }

    @Override
    public void exitSql(SqlParser.SqlContext ctx) {
        super.exitSql(ctx);
        System.out.println("Exist SQL: " + ctx.getText());

    }

    @Override
    public void enterFields(SqlParser.FieldsContext ctx) {
        super.enterFields(ctx);
        System.out.println("Field: " + ctx.getText());
        ctx.fields();

    }

    @Override
    public void exitFields(SqlParser.FieldsContext ctx) {
        super.exitFields(ctx);
    }

    @Override
    public void enterFrom_stat(SqlParser.From_statContext ctx) {
        super.enterFrom_stat(ctx);
    }

    @Override
    public void exitFrom_stat(SqlParser.From_statContext ctx) {
        super.exitFrom_stat(ctx);
        ctx.full_table_name();
    }

    @Override
    public void enterFull_table_name(SqlParser.Full_table_nameContext ctx) {
        super.enterFull_table_name(ctx);
    }

    @Override
    public void exitFull_table_name(SqlParser.Full_table_nameContext ctx) {
        super.exitFull_table_name(ctx);
    }

    @Override
    public void enterField(SqlParser.FieldContext ctx) {
        super.enterField(ctx);
    }

    @Override
    public void exitField(SqlParser.FieldContext ctx) {
        super.exitField(ctx);
    }

    @Override
    public void enterFull_field_name(SqlParser.Full_field_nameContext ctx) {
        super.enterFull_field_name(ctx);
    }

    @Override
    public void exitFull_field_name(SqlParser.Full_field_nameContext ctx) {
        super.exitFull_field_name(ctx);
    }

    @Override
    public void enterDb_name(SqlParser.Db_nameContext ctx) {
        super.enterDb_name(ctx);
    }

    @Override
    public void exitDb_name(SqlParser.Db_nameContext ctx) {
        super.exitDb_name(ctx);
    }

    @Override
    public void enterTable_name(SqlParser.Table_nameContext ctx) {
        super.enterTable_name(ctx);
    }

    @Override
    public void exitTable_name(SqlParser.Table_nameContext ctx) {
        super.exitTable_name(ctx);
    }

    @Override
    public void enterAlias_name(SqlParser.Alias_nameContext ctx) {
        super.enterAlias_name(ctx);
    }

    @Override
    public void exitAlias_name(SqlParser.Alias_nameContext ctx) {
        super.exitAlias_name(ctx);
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        super.enterEveryRule(ctx);
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        super.exitEveryRule(ctx);
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        super.visitTerminal(node);
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        super.visitErrorNode(node);
    }

    public static void main(String[] args) {
        ANTLRInputStream input = new ANTLRInputStream("select a.b.a_field, a.b.b_field from a.b");
        SqlLexer lexer = new SqlLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SqlParser parser = new SqlParser(tokens);
        ParseTree tree = parser.sql();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new SQLVisitor(), tree);
        System.out.println(tree.toStringTree(parser));
    }

}
