/*
 * Copyright 1999-2101 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.druid.sql.dialect.mysql.visitor;

import com.alibaba.druid.sql.ast.SQLName;
import com.alibaba.druid.sql.ast.SQLObject;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.statement.*;
import com.alibaba.druid.sql.dialect.mysql.ast.*;
import com.alibaba.druid.sql.dialect.mysql.ast.clause.*;
import com.alibaba.druid.sql.dialect.mysql.ast.clause.MySqlCaseStatement.MySqlWhenStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.clause.MySqlIfStatement.MySqlElseIfStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.expr.*;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.*;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlCreateTableStatement.TableSpaceOption;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlCreateUserStatement.UserSpecification;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlPartitioningDef.InValues;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlPartitioningDef.LessThanValues;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock.Limit;
import com.alibaba.druid.sql.visitor.SchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.stat.TableStat.Mode;
import com.alibaba.druid.util.JdbcUtils;

import java.util.Map;

public class MySqlSchemaStatVisitor extends SchemaStatVisitor implements MySqlASTVisitor {

    public boolean visit(SQLSelectStatement x) {
        setAliasMap();
        getAliasMap().put("DUAL", null);

        return true;
    }

    @Override
    public String getDbType() {
        return JdbcUtils.MYSQL;
    }

    // DUAL
    public boolean visit(MySqlDeleteStatement x) {
        setAliasMap();

        setMode(x, Mode.Delete);

        accept(x.getFrom());
        accept(x.getUsing());
        x.getTableSource().accept(this);

        if (x.getTableSource() instanceof SQLExprTableSource) {
            SQLName tableName = (SQLName) ((SQLExprTableSource) x.getTableSource()).getExpr();
            String ident = tableName.toString();
            setCurrentTable(x, ident);

            TableStat stat = this.getTableStat(ident);
            stat.incrementDeleteCount();
        }

        accept(x.getWhere());

        accept(x.getOrderBy());
        accept(x.getLimit());

        return false;
    }

    public void endVisit(MySqlDeleteStatement x) {
        setAliasMap(null);
    }

    @Override
    public void endVisit(MySqlInsertStatement x) {
        setModeOrigin(x);
    }

    @Override
    public boolean visit(MySqlInsertStatement x) {
        setMode(x, Mode.Insert);

        setAliasMap();

        if (x.getTableName() instanceof SQLIdentifierExpr) {
            String ident = ((SQLIdentifierExpr) x.getTableName()).getName();
            setCurrentTable(x, ident);

            TableStat stat = getTableStat(ident);
            stat.incrementInsertCount();

            Map<String, String> aliasMap = getAliasMap();
            if (aliasMap != null) {
                if (x.getAlias() != null) {
                    aliasMap.put(x.getAlias(), ident);
                }
                aliasMap.put(ident, ident);
            }
        }

        accept(x.getColumns());
        accept(x.getValuesList());
        accept(x.getQuery());
        accept(x.getDuplicateKeyUpdate());

        return false;
    }

    @Override
    public boolean visit(Limit x) {

        return true;
    }

    @Override
    public void endVisit(Limit x) {

    }

    @Override
    public boolean visit(MySqlTableIndex x) {

        return false;
    }

    @Override
    public void endVisit(MySqlTableIndex x) {

    }

    @Override
    public boolean visit(MySqlKey x) {
        return false;
    }

    @Override
    public void endVisit(MySqlKey x) {

    }

    @Override
    public boolean visit(MySqlPrimaryKey x) {
        for (SQLObject item : x.getColumns()) {
            item.accept(this);
        }
        return false;
    }

    @Override
    public void endVisit(MySqlPrimaryKey x) {

    }

    @Override
    public void endVisit(MySqlIntervalExpr x) {

    }

    @Override
    public boolean visit(MySqlIntervalExpr x) {

        return true;
    }

    @Override
    public void endVisit(MySqlExtractExpr x) {

    }

    @Override
    public boolean visit(MySqlExtractExpr x) {

        return true;
    }

    @Override
    public void endVisit(MySqlMatchAgainstExpr x) {

    }

    @Override
    public boolean visit(MySqlMatchAgainstExpr x) {

        return true;
    }

    @Override
    public void endVisit(MySqlPrepareStatement x) {

    }

    @Override
    public boolean visit(MySqlPrepareStatement x) {

        return true;
    }

    @Override
    public void endVisit(MySqlExecuteStatement x) {

    }

    @Override
    public boolean visit(MySqlExecuteStatement x) {

        return true;
    }

    @Override
    public void endVisit(MySqlLoadDataInFileStatement x) {

    }

    @Override
    public boolean visit(MySqlLoadDataInFileStatement x) {

        return true;
    }

    @Override
    public void endVisit(MySqlLoadXmlStatement x) {

    }

    @Override
    public boolean visit(MySqlLoadXmlStatement x) {

        return true;
    }

    @Override
    public void endVisit(MySqlReplaceStatement x) {

    }

    @Override
    public boolean visit(MySqlReplaceStatement x) {

        return true;
    }

    @Override
    public void endVisit(MySqlSelectGroupBy x) {

    }

    @Override
    public boolean visit(MySqlSelectGroupBy x) {

        return true;
    }

    @Override
    public void endVisit(MySqlStartTransactionStatement x) {

    }

    @Override
    public boolean visit(MySqlStartTransactionStatement x) {

        return true;
    }

    @Override
    public void endVisit(MySqlCommitStatement x) {

    }

    @Override
    public boolean visit(MySqlCommitStatement x) {

        return true;
    }

    @Override
    public void endVisit(MySqlRollbackStatement x) {

    }

    @Override
    public boolean visit(MySqlRollbackStatement x) {

        return true;
    }

    @Override
    public void endVisit(MySqlShowColumnsStatement x) {

    }

    @Override
    public boolean visit(MySqlShowColumnsStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowTablesStatement x) {

    }

    @Override
    public boolean visit(MySqlShowTablesStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowDatabasesStatement x) {

    }

    @Override
    public boolean visit(MySqlShowDatabasesStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowWarningsStatement x) {

    }

    @Override
    public boolean visit(MySqlShowWarningsStatement x) {
        return true;
    }

    @Override
    public void endVisit(MySqlShowStatusStatement x) {

    }

    @Override
    public boolean visit(MySqlShowStatusStatement x) {
        return true;
    }

    @Override
    public void endVisit(CobarShowStatus x) {

    }

    @Override
    public boolean visit(CobarShowStatus x) {
        return true;
    }

    @Override
    public void endVisit(MySqlKillStatement x) {

    }

    @Override
    public boolean visit(MySqlKillStatement x) {
        return true;
    }

    @Override
    public void endVisit(MySqlBinlogStatement x) {

    }

    @Override
    public boolean visit(MySqlBinlogStatement x) {
        return true;
    }

    @Override
    public void endVisit(MySqlResetStatement x) {

    }

    @Override
    public boolean visit(MySqlResetStatement x) {
        return true;
    }

    @Override
    public void endVisit(MySqlCreateUserStatement x) {

    }

    @Override
    public boolean visit(MySqlCreateUserStatement x) {
        return false;
    }

    @Override
    public void endVisit(UserSpecification x) {

    }

    @Override
    public boolean visit(UserSpecification x) {
        return true;
    }

    @Override
    public void endVisit(MySqlPartitionByKey x) {

    }

    @Override
    public boolean visit(MySqlPartitionByKey x) {
        accept(x.getColumns());
        return false;
    }

    @Override
    public boolean visit(MySqlSelectQueryBlock x) {
        return this.visit((SQLSelectQueryBlock) x);
    }

    @Override
    public void endVisit(MySqlSelectQueryBlock x) {

    }

    @Override
    public boolean visit(MySqlOutFileExpr x) {
        return false;
    }

    @Override
    public void endVisit(MySqlOutFileExpr x) {

    }

    @Override
    public boolean visit(MySqlDescribeStatement x) {
        String table = x.getObject().toString();
        getTableStat(table);
        if (x.getColName() != null) {
            addColumn(table, x.getColName().toString());
        }
        return false;
    }

    @Override
    public void endVisit(MySqlDescribeStatement x) {

    }

    @Override
    public boolean visit(MySqlUpdateStatement x) {
        return visit((SQLUpdateStatement) x);
    }

    @Override
    public void endVisit(MySqlUpdateStatement x) {

    }

    @Override
    public boolean visit(MySqlSetTransactionStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlSetTransactionStatement x) {

    }

    @Override
    public boolean visit(MySqlSetNamesStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlSetNamesStatement x) {

    }

    @Override
    public boolean visit(MySqlSetCharSetStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlSetCharSetStatement x) {

    }

    @Override
    public boolean visit(MySqlShowAuthorsStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowAuthorsStatement x) {

    }

    @Override
    public boolean visit(MySqlShowBinaryLogsStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowBinaryLogsStatement x) {

    }

    @Override
    public boolean visit(MySqlShowMasterLogsStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowMasterLogsStatement x) {

    }

    @Override
    public boolean visit(MySqlShowCollationStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowCollationStatement x) {

    }

    @Override
    public boolean visit(MySqlShowBinLogEventsStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowBinLogEventsStatement x) {

    }

    @Override
    public boolean visit(MySqlShowCharacterSetStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowCharacterSetStatement x) {

    }

    @Override
    public boolean visit(MySqlShowContributorsStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowContributorsStatement x) {

    }

    @Override
    public boolean visit(MySqlShowCreateDatabaseStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowCreateDatabaseStatement x) {

    }

    @Override
    public boolean visit(MySqlShowCreateEventStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowCreateEventStatement x) {

    }

    @Override
    public boolean visit(MySqlShowCreateFunctionStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowCreateFunctionStatement x) {

    }

    @Override
    public boolean visit(MySqlShowCreateProcedureStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowCreateProcedureStatement x) {

    }

    @Override
    public boolean visit(MySqlShowCreateTableStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowCreateTableStatement x) {

    }

    @Override
    public boolean visit(MySqlShowCreateTriggerStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowCreateTriggerStatement x) {

    }

    @Override
    public boolean visit(MySqlShowCreateViewStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowCreateViewStatement x) {

    }

    @Override
    public boolean visit(MySqlShowEngineStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowEngineStatement x) {

    }

    @Override
    public boolean visit(MySqlShowEnginesStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowEnginesStatement x) {

    }

    @Override
    public boolean visit(MySqlShowErrorsStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowErrorsStatement x) {

    }

    @Override
    public boolean visit(MySqlShowEventsStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowEventsStatement x) {

    }

    @Override
    public boolean visit(MySqlShowFunctionCodeStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowFunctionCodeStatement x) {

    }

    @Override
    public boolean visit(MySqlShowFunctionStatusStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowFunctionStatusStatement x) {

    }

    @Override
    public boolean visit(MySqlShowGrantsStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowGrantsStatement x) {

    }

    @Override
    public boolean visit(MySqlUserName x) {
        return false;
    }

    @Override
    public void endVisit(MySqlUserName x) {

    }

    @Override
    public boolean visit(MySqlShowIndexesStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowIndexesStatement x) {

    }

    @Override
    public boolean visit(MySqlShowKeysStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowKeysStatement x) {

    }

    @Override
    public boolean visit(MySqlShowMasterStatusStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowMasterStatusStatement x) {

    }

    @Override
    public boolean visit(MySqlShowOpenTablesStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowOpenTablesStatement x) {

    }

    @Override
    public boolean visit(MySqlShowPluginsStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowPluginsStatement x) {

    }

    @Override
    public boolean visit(MySqlShowPrivilegesStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowPrivilegesStatement x) {

    }

    @Override
    public boolean visit(MySqlShowProcedureCodeStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowProcedureCodeStatement x) {

    }

    @Override
    public boolean visit(MySqlShowProcedureStatusStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowProcedureStatusStatement x) {

    }

    @Override
    public boolean visit(MySqlShowProcessListStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowProcessListStatement x) {

    }

    @Override
    public boolean visit(MySqlShowProfileStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowProfileStatement x) {

    }

    @Override
    public boolean visit(MySqlShowProfilesStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowProfilesStatement x) {

    }

    @Override
    public boolean visit(MySqlShowRelayLogEventsStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowRelayLogEventsStatement x) {

    }

    @Override
    public boolean visit(MySqlShowSlaveHostsStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowSlaveHostsStatement x) {

    }

    @Override
    public boolean visit(MySqlShowSlaveStatusStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowSlaveStatusStatement x) {

    }

    @Override
    public boolean visit(MySqlShowTableStatusStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowTableStatusStatement x) {

    }

    @Override
    public boolean visit(MySqlShowTriggersStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowTriggersStatement x) {

    }

    @Override
    public boolean visit(MySqlShowVariantsStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowVariantsStatement x) {

    }

    @Override
    public boolean visit(MySqlAlterTableStatement x) {
        return visit((SQLAlterTableStatement) x);
    }

    @Override
    public void endVisit(MySqlAlterTableStatement x) {

    }

    @Override
    public boolean visit(MySqlAlterTableAddColumn x) {
        return visit((SQLAlterTableAddColumn) x);
    }

    @Override
    public void endVisit(MySqlAlterTableAddColumn x) {

    }

    @Override
    public boolean visit(MySqlCreateIndexStatement x) {
        return visit((SQLCreateIndexStatement) x);
    }

    @Override
    public void endVisit(MySqlCreateIndexStatement x) {

    }

    @Override
    public boolean visit(MySqlRenameTableStatement.Item x) {
        return false;
    }

    @Override
    public void endVisit(MySqlRenameTableStatement.Item x) {

    }

    @Override
    public boolean visit(MySqlRenameTableStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlRenameTableStatement x) {

    }

    @Override
    public boolean visit(MySqlUnionQuery x) {
        return visit((SQLUnionQuery) x);
    }

    @Override
    public void endVisit(MySqlUnionQuery x) {

    }

    @Override
    public boolean visit(MySqlUseIndexHint x) {
        return false;
    }

    @Override
    public void endVisit(MySqlUseIndexHint x) {

    }

    @Override
    public boolean visit(MySqlIgnoreIndexHint x) {
        return false;
    }

    @Override
    public void endVisit(MySqlIgnoreIndexHint x) {

    }

    @Override
    public boolean visit(MySqlLockTableStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlLockTableStatement x) {

    }

    @Override
    public boolean visit(MySqlUnlockTablesStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlUnlockTablesStatement x) {

    }

    @Override
    public boolean visit(MySqlForceIndexHint x) {
        return false;
    }

    @Override
    public void endVisit(MySqlForceIndexHint x) {

    }

    @Override
    public boolean visit(MySqlAlterTableChangeColumn x) {
        SQLAlterTableStatement stmt = (SQLAlterTableStatement) x.getParent();
        String table = stmt.getName().toString();

        String columnName = x.getColumnName().toString();
        addColumn(table, columnName);
        return false;
    }

    @Override
    public void endVisit(MySqlAlterTableChangeColumn x) {

    }

    @Override
    public boolean visit(MySqlAlterTableModifyColumn x) {
        SQLAlterTableStatement stmt = (SQLAlterTableStatement) x.getParent();
        String table = stmt.getName().toString();

        String columnName = x.getNewColumnDefinition().getName().toString();
        addColumn(table, columnName);
        return false;
    }

    @Override
    public void endVisit(MySqlAlterTableModifyColumn x) {

    }

    @Override
    public boolean visit(MySqlAlterTableCharacter x) {
        return false;
    }

    @Override
    public void endVisit(MySqlAlterTableCharacter x) {

    }

    @Override
    public boolean visit(MySqlAlterTableOption x) {
        return false;
    }

    @Override
    public void endVisit(MySqlAlterTableOption x) {

    }

    @Override
    public boolean visit(MySqlCreateTableStatement x) {
        boolean val = super.visit((SQLCreateTableStatement) x);

        for (SQLObject option : x.getTableOptions().values()) {
            if (option instanceof SQLTableSource) {
                option.accept(this);
            }
        }

        return val;
    }

    @Override
    public void endVisit(MySqlCreateTableStatement x) {

    }

    @Override
    public boolean visit(MySqlHelpStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlHelpStatement x) {

    }

    @Override
    public boolean visit(MySqlCharExpr x) {
        return false;
    }

    @Override
    public void endVisit(MySqlCharExpr x) {

    }

    @Override
    public boolean visit(MySqlUnique x) {
        return false;
    }

    @Override
    public void endVisit(MySqlUnique x) {

    }

    @Override
    public boolean visit(MysqlForeignKey x) {
        return super.visit(x);
    }

    @Override
    public void endVisit(MysqlForeignKey x) {

    }

    @Override
    public boolean visit(MySqlAlterTableDiscardTablespace x) {
        return false;
    }

    @Override
    public void endVisit(MySqlAlterTableDiscardTablespace x) {

    }

    @Override
    public boolean visit(MySqlAlterTableImportTablespace x) {
        return false;
    }

    @Override
    public void endVisit(MySqlAlterTableImportTablespace x) {

    }

    @Override
    public boolean visit(TableSpaceOption x) {
        return false;
    }

    @Override
    public void endVisit(TableSpaceOption x) {

    }

    @Override
    public boolean visit(MySqlPartitionByHash x) {
        return false;
    }

    @Override
    public void endVisit(MySqlPartitionByHash x) {

    }

    @Override
    public boolean visit(MySqlPartitionByRange x) {
        return false;
    }

    @Override
    public void endVisit(MySqlPartitionByRange x) {

    }

    @Override
    public boolean visit(MySqlPartitioningDef x) {
        return false;
    }

    @Override
    public void endVisit(MySqlPartitioningDef x) {

    }

    @Override
    public boolean visit(LessThanValues x) {
        return false;
    }

    @Override
    public void endVisit(LessThanValues x) {

    }

    @Override
    public boolean visit(InValues x) {
        return false;
    }

    @Override
    public void endVisit(InValues x) {

    }

    @Override
    public boolean visit(MySqlPartitionByList x) {
        return false;
    }

    @Override
    public void endVisit(MySqlPartitionByList x) {

    }

    @Override
    public boolean visit(MySqlAnalyzeStatement x) {
        return true;
    }

    @Override
    public void endVisit(MySqlAnalyzeStatement x) {

    }

    @Override
    public boolean visit(MySqlAlterUserStatement x) {
        return true;
    }

    @Override
    public void endVisit(MySqlAlterUserStatement x) {

    }

    @Override
    public boolean visit(MySqlOptimizeStatement x) {
        return true;
    }

    @Override
    public void endVisit(MySqlOptimizeStatement x) {

    }

    @Override
    public boolean visit(MySqlSetPasswordStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlSetPasswordStatement x) {

    }

    @Override
    public boolean visit(MySqlHintStatement x) {
        return true;
    }

    @Override
    public void endVisit(MySqlHintStatement x) {

    }

    @Override
    public boolean visit(MySqlSelectGroupByExpr x) {
        return true;
    }

    @Override
    public void endVisit(MySqlSelectGroupByExpr x) {

    }

    @Override
    public boolean visit(MySqlBlockStatement x) {
        return true;
    }

    @Override
    public void endVisit(MySqlBlockStatement x) {

    }

    /**
     * support procedure
     */
    @Override
    public boolean visit(MySqlCreateProcedureStatement x) {
        // TODO Auto-generated method stub
        String name = x.getName().toString();
        this.variants.put(name, x);
        accept(x.getBlock());
        return false;
    }

    @Override
    public void endVisit(MySqlCreateProcedureStatement x) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean visit(MySqlParameter x) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public void endVisit(MySqlParameter x) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean visit(MySqlWhileStatement x) {
        accept(x.getStatements());
        return false;
    }

    @Override
    public void endVisit(MySqlWhileStatement x) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean visit(MySqlIfStatement x) {
        accept(x.getStatements());
        accept(x.getElseIfList());
        accept(x.getElseItem());
        return false;
    }

    @Override
    public void endVisit(MySqlIfStatement x) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean visit(MySqlElseIfStatement x) {
        accept(x.getStatements());
        return false;
    }

    @Override
    public void endVisit(MySqlElseIfStatement x) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean visit(MySqlElseStatement x) {
        accept(x.getStatements());
        return false;
    }

    @Override
    public void endVisit(MySqlElseStatement x) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean visit(MySqlCaseStatement x) {
        accept(x.getWhenList());
        return false;
    }

    @Override
    public void endVisit(MySqlCaseStatement x) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean visit(MySqlDeclareStatement x) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void endVisit(MySqlDeclareStatement x) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean visit(MySqlSelectIntoStatement x) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void endVisit(MySqlSelectIntoStatement x) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean visit(MySqlWhenStatement x) {
        accept(x.getStatements());
        return false;
    }

    @Override
    public void endVisit(MySqlWhenStatement x) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean visit(MySqlLoopStatement x) {
        accept(x.getStatements());
        return false;
    }

    @Override
    public void endVisit(MySqlLoopStatement x) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean visit(MySqlLeaveStatement x) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void endVisit(MySqlLeaveStatement x) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean visit(MySqlIterateStatement x) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void endVisit(MySqlIterateStatement x) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean visit(MySqlRepeatStatement x) {
        // TODO Auto-generated method stub
        accept(x.getStatements());
        return false;
    }

    @Override
    public void endVisit(MySqlRepeatStatement x) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean visit(MySqlCursorDeclareStatement x) {
        accept(x.getSelect());
        return false;
    }

    @Override
    public void endVisit(MySqlCursorDeclareStatement x) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean visit(MySqlUpdateTableSource x) {
        if (x.getUpdate() != null) {
            return this.visit(x.getUpdate());
        }
        return false;
    }

    @Override
    public void endVisit(MySqlUpdateTableSource x) {

    }

}
