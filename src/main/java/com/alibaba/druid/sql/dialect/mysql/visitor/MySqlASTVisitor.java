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

import com.alibaba.druid.sql.dialect.mysql.ast.*;
import com.alibaba.druid.sql.dialect.mysql.ast.clause.*;
import com.alibaba.druid.sql.dialect.mysql.ast.clause.MySqlCaseStatement.MySqlWhenStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.clause.MySqlIfStatement.MySqlElseIfStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.expr.*;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.*;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;

public interface MySqlASTVisitor extends SQLASTVisitor {
    boolean visit(MySqlSelectQueryBlock.Limit x);

    void endVisit(MySqlSelectQueryBlock.Limit x);

    boolean visit(MySqlTableIndex x);

    void endVisit(MySqlTableIndex x);

    boolean visit(MySqlKey x);

    void endVisit(MySqlKey x);

    boolean visit(MySqlPrimaryKey x);

    void endVisit(MySqlPrimaryKey x);

    boolean visit(MySqlUnique x);

    void endVisit(MySqlUnique x);

    boolean visit(MysqlForeignKey x);

    void endVisit(MysqlForeignKey x);

    void endVisit(MySqlIntervalExpr x);

    boolean visit(MySqlIntervalExpr x);

    void endVisit(MySqlExtractExpr x);

    boolean visit(MySqlExtractExpr x);

    void endVisit(MySqlMatchAgainstExpr x);

    boolean visit(MySqlMatchAgainstExpr x);

    void endVisit(MySqlPrepareStatement x);

    boolean visit(MySqlPrepareStatement x);

    void endVisit(MySqlExecuteStatement x);

    boolean visit(MySqlExecuteStatement x);

    void endVisit(MySqlDeleteStatement x);

    boolean visit(MySqlDeleteStatement x);

    void endVisit(MySqlInsertStatement x);

    boolean visit(MySqlInsertStatement x);

    void endVisit(MySqlLoadDataInFileStatement x);

    boolean visit(MySqlLoadDataInFileStatement x);

    void endVisit(MySqlLoadXmlStatement x);

    boolean visit(MySqlLoadXmlStatement x);

    void endVisit(MySqlReplaceStatement x);

    boolean visit(MySqlReplaceStatement x);

    void endVisit(MySqlSelectGroupBy x);

    boolean visit(MySqlSelectGroupBy x);

    void endVisit(MySqlStartTransactionStatement x);

    boolean visit(MySqlStartTransactionStatement x);

    void endVisit(MySqlCommitStatement x);

    boolean visit(MySqlCommitStatement x);

    void endVisit(MySqlRollbackStatement x);

    boolean visit(MySqlRollbackStatement x);

    void endVisit(MySqlShowColumnsStatement x);

    boolean visit(MySqlShowColumnsStatement x);

    void endVisit(MySqlShowTablesStatement x);

    boolean visit(MySqlShowTablesStatement x);

    void endVisit(MySqlShowDatabasesStatement x);

    boolean visit(MySqlShowDatabasesStatement x);

    void endVisit(MySqlShowWarningsStatement x);

    boolean visit(MySqlShowWarningsStatement x);

    void endVisit(MySqlShowStatusStatement x);

    boolean visit(MySqlShowStatusStatement x);

    void endVisit(MySqlShowAuthorsStatement x);

    boolean visit(MySqlShowAuthorsStatement x);

    void endVisit(CobarShowStatus x);

    boolean visit(CobarShowStatus x);

    void endVisit(MySqlKillStatement x);

    boolean visit(MySqlKillStatement x);

    void endVisit(MySqlBinlogStatement x);

    boolean visit(MySqlBinlogStatement x);

    void endVisit(MySqlResetStatement x);

    boolean visit(MySqlResetStatement x);

    void endVisit(MySqlCreateUserStatement x);

    boolean visit(MySqlCreateUserStatement x);

    void endVisit(MySqlCreateUserStatement.UserSpecification x);

    boolean visit(MySqlCreateUserStatement.UserSpecification x);

    void endVisit(MySqlPartitionByKey x);

    boolean visit(MySqlPartitionByKey x);

    boolean visit(MySqlSelectQueryBlock x);

    void endVisit(MySqlSelectQueryBlock x);

    boolean visit(MySqlOutFileExpr x);

    void endVisit(MySqlOutFileExpr x);

    boolean visit(MySqlDescribeStatement x);

    void endVisit(MySqlDescribeStatement x);

    boolean visit(MySqlUpdateStatement x);

    void endVisit(MySqlUpdateStatement x);

    boolean visit(MySqlSetTransactionStatement x);

    void endVisit(MySqlSetTransactionStatement x);

    boolean visit(MySqlSetNamesStatement x);

    void endVisit(MySqlSetNamesStatement x);

    boolean visit(MySqlSetCharSetStatement x);

    void endVisit(MySqlSetCharSetStatement x);

    boolean visit(MySqlShowBinaryLogsStatement x);

    void endVisit(MySqlShowBinaryLogsStatement x);

    boolean visit(MySqlShowMasterLogsStatement x);

    void endVisit(MySqlShowMasterLogsStatement x);

    boolean visit(MySqlShowCharacterSetStatement x);

    void endVisit(MySqlShowCharacterSetStatement x);

    boolean visit(MySqlShowCollationStatement x);

    void endVisit(MySqlShowCollationStatement x);

    boolean visit(MySqlShowBinLogEventsStatement x);

    void endVisit(MySqlShowBinLogEventsStatement x);

    boolean visit(MySqlShowContributorsStatement x);

    void endVisit(MySqlShowContributorsStatement x);

    boolean visit(MySqlShowCreateDatabaseStatement x);

    void endVisit(MySqlShowCreateDatabaseStatement x);

    boolean visit(MySqlShowCreateEventStatement x);

    void endVisit(MySqlShowCreateEventStatement x);

    boolean visit(MySqlShowCreateFunctionStatement x);

    void endVisit(MySqlShowCreateFunctionStatement x);

    boolean visit(MySqlShowCreateProcedureStatement x);

    void endVisit(MySqlShowCreateProcedureStatement x);

    boolean visit(MySqlShowCreateTableStatement x);

    void endVisit(MySqlShowCreateTableStatement x);

    boolean visit(MySqlShowCreateTriggerStatement x);

    void endVisit(MySqlShowCreateTriggerStatement x);

    boolean visit(MySqlShowCreateViewStatement x);

    void endVisit(MySqlShowCreateViewStatement x);

    boolean visit(MySqlShowEngineStatement x);

    void endVisit(MySqlShowEngineStatement x);

    boolean visit(MySqlShowEnginesStatement x);

    void endVisit(MySqlShowEnginesStatement x);

    boolean visit(MySqlShowErrorsStatement x);

    void endVisit(MySqlShowErrorsStatement x);

    boolean visit(MySqlShowEventsStatement x);

    void endVisit(MySqlShowEventsStatement x);

    boolean visit(MySqlShowFunctionCodeStatement x);

    void endVisit(MySqlShowFunctionCodeStatement x);

    boolean visit(MySqlShowFunctionStatusStatement x);

    void endVisit(MySqlShowFunctionStatusStatement x);

    boolean visit(MySqlShowGrantsStatement x);

    void endVisit(MySqlShowGrantsStatement x);

    boolean visit(MySqlUserName x);

    void endVisit(MySqlUserName x);

    boolean visit(MySqlShowIndexesStatement x);

    void endVisit(MySqlShowIndexesStatement x);

    boolean visit(MySqlShowKeysStatement x);

    void endVisit(MySqlShowKeysStatement x);

    boolean visit(MySqlShowMasterStatusStatement x);

    void endVisit(MySqlShowMasterStatusStatement x);

    boolean visit(MySqlShowOpenTablesStatement x);

    void endVisit(MySqlShowOpenTablesStatement x);

    boolean visit(MySqlShowPluginsStatement x);

    void endVisit(MySqlShowPluginsStatement x);

    boolean visit(MySqlShowPrivilegesStatement x);

    void endVisit(MySqlShowPrivilegesStatement x);

    boolean visit(MySqlShowProcedureCodeStatement x);

    void endVisit(MySqlShowProcedureCodeStatement x);

    boolean visit(MySqlShowProcedureStatusStatement x);

    void endVisit(MySqlShowProcedureStatusStatement x);

    boolean visit(MySqlShowProcessListStatement x);

    void endVisit(MySqlShowProcessListStatement x);

    boolean visit(MySqlShowProfileStatement x);

    void endVisit(MySqlShowProfileStatement x);

    boolean visit(MySqlShowProfilesStatement x);

    void endVisit(MySqlShowProfilesStatement x);

    boolean visit(MySqlShowRelayLogEventsStatement x);

    void endVisit(MySqlShowRelayLogEventsStatement x);

    boolean visit(MySqlShowSlaveHostsStatement x);

    void endVisit(MySqlShowSlaveHostsStatement x);

    boolean visit(MySqlShowSlaveStatusStatement x);

    void endVisit(MySqlShowSlaveStatusStatement x);

    boolean visit(MySqlShowTableStatusStatement x);

    void endVisit(MySqlShowTableStatusStatement x);

    boolean visit(MySqlShowTriggersStatement x);

    void endVisit(MySqlShowTriggersStatement x);

    boolean visit(MySqlShowVariantsStatement x);

    void endVisit(MySqlShowVariantsStatement x);

    boolean visit(MySqlAlterTableStatement x);

    void endVisit(MySqlAlterTableStatement x);

    boolean visit(MySqlAlterTableAddColumn x);

    void endVisit(MySqlAlterTableAddColumn x);

    boolean visit(MySqlCreateIndexStatement x);

    void endVisit(MySqlCreateIndexStatement x);

    boolean visit(MySqlRenameTableStatement.Item x);

    void endVisit(MySqlRenameTableStatement.Item x);

    boolean visit(MySqlRenameTableStatement x);

    void endVisit(MySqlRenameTableStatement x);

    boolean visit(MySqlUnionQuery x);

    void endVisit(MySqlUnionQuery x);

    boolean visit(MySqlUseIndexHint x);

    void endVisit(MySqlUseIndexHint x);

    boolean visit(MySqlIgnoreIndexHint x);

    void endVisit(MySqlIgnoreIndexHint x);

    boolean visit(MySqlLockTableStatement x);

    void endVisit(MySqlLockTableStatement x);

    boolean visit(MySqlUnlockTablesStatement x);

    void endVisit(MySqlUnlockTablesStatement x);

    boolean visit(MySqlForceIndexHint x);

    void endVisit(MySqlForceIndexHint x);

    boolean visit(MySqlAlterTableChangeColumn x);

    void endVisit(MySqlAlterTableChangeColumn x);

    boolean visit(MySqlAlterTableCharacter x);

    void endVisit(MySqlAlterTableCharacter x);

    boolean visit(MySqlAlterTableOption x);

    void endVisit(MySqlAlterTableOption x);

    boolean visit(MySqlCreateTableStatement x);

    void endVisit(MySqlCreateTableStatement x);

    boolean visit(MySqlHelpStatement x);

    void endVisit(MySqlHelpStatement x);

    boolean visit(MySqlCharExpr x);

    void endVisit(MySqlCharExpr x);

    boolean visit(MySqlAlterTableModifyColumn x);

    void endVisit(MySqlAlterTableModifyColumn x);

    boolean visit(MySqlAlterTableDiscardTablespace x);

    void endVisit(MySqlAlterTableDiscardTablespace x);

    boolean visit(MySqlAlterTableImportTablespace x);

    void endVisit(MySqlAlterTableImportTablespace x);

    boolean visit(MySqlCreateTableStatement.TableSpaceOption x);

    void endVisit(MySqlCreateTableStatement.TableSpaceOption x);

    boolean visit(MySqlPartitionByHash x);

    void endVisit(MySqlPartitionByHash x);

    boolean visit(MySqlPartitionByRange x);

    void endVisit(MySqlPartitionByRange x);

    boolean visit(MySqlPartitionByList x);

    void endVisit(MySqlPartitionByList x);

    boolean visit(MySqlPartitioningDef x);

    void endVisit(MySqlPartitioningDef x);

    boolean visit(MySqlPartitioningDef.LessThanValues x);

    void endVisit(MySqlPartitioningDef.LessThanValues x);

    boolean visit(MySqlPartitioningDef.InValues x);

    void endVisit(MySqlPartitioningDef.InValues x);

    boolean visit(MySqlAnalyzeStatement x);

    void endVisit(MySqlAnalyzeStatement x);

    boolean visit(MySqlAlterUserStatement x);

    void endVisit(MySqlAlterUserStatement x);

    boolean visit(MySqlOptimizeStatement x);

    void endVisit(MySqlOptimizeStatement x);

    boolean visit(MySqlSetPasswordStatement x);

    void endVisit(MySqlSetPasswordStatement x);

    boolean visit(MySqlHintStatement x);

    void endVisit(MySqlHintStatement x);

    boolean visit(MySqlSelectGroupByExpr x);

    void endVisit(MySqlSelectGroupByExpr x);

    boolean visit(MySqlBlockStatement x);

    void endVisit(MySqlBlockStatement x);


    /**
     * support procedure
     */
    boolean visit(MySqlCreateProcedureStatement x);

    void endVisit(MySqlCreateProcedureStatement x);

    boolean visit(MySqlParameter x);

    void endVisit(MySqlParameter x);

    boolean visit(MySqlWhileStatement x);

    void endVisit(MySqlWhileStatement x);

    boolean visit(MySqlIfStatement x);

    void endVisit(MySqlIfStatement x);

    boolean visit(MySqlElseIfStatement x);

    void endVisit(MySqlElseIfStatement x);

    boolean visit(MySqlElseStatement x);

    void endVisit(MySqlElseStatement x);

    boolean visit(MySqlCaseStatement x);

    void endVisit(MySqlCaseStatement x);

    boolean visit(MySqlDeclareStatement x);

    void endVisit(MySqlDeclareStatement x);

    boolean visit(MySqlSelectIntoStatement x);

    void endVisit(MySqlSelectIntoStatement x);

    boolean visit(MySqlWhenStatement x);

    void endVisit(MySqlWhenStatement x);

    boolean visit(MySqlLoopStatement x);

    void endVisit(MySqlLoopStatement x);

    boolean visit(MySqlLeaveStatement x);

    void endVisit(MySqlLeaveStatement x);

    boolean visit(MySqlIterateStatement x);

    void endVisit(MySqlIterateStatement x);

    boolean visit(MySqlRepeatStatement x);

    void endVisit(MySqlRepeatStatement x);

    boolean visit(MySqlCursorDeclareStatement x);

    void endVisit(MySqlCursorDeclareStatement x);

    boolean visit(MySqlUpdateTableSource x);

    void endVisit(MySqlUpdateTableSource x);

} //
