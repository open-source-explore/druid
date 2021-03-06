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
package com.alibaba.druid.sql.dialect.mysql.ast.clause;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.MySqlObjectImpl;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlStatementImpl;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlASTVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zz email:455910092@qq.com
 * @version V1.0
 * @Description: MySql procedure if statement
 * @date 2015-9-14
 */
public class MySqlIfStatement extends MySqlStatementImpl {
    private SQLExpr condition;
    private List<SQLStatement> statements = new ArrayList<SQLStatement>();
    private List<MySqlElseIfStatement> elseIfList = new ArrayList<MySqlElseIfStatement>();
    private MySqlElseStatement elseItem;

    @Override
    public void accept0(MySqlASTVisitor visitor) {
        if (visitor.visit(this)) {
            acceptChild(visitor, condition);
            acceptChild(visitor, statements);
            acceptChild(visitor, elseIfList);
            acceptChild(visitor, elseItem);
        }
        visitor.endVisit(this);
    }

    public SQLExpr getCondition() {
        return condition;
    }

    public void setCondition(SQLExpr condition) {
        this.condition = condition;
    }

    public List<SQLStatement> getStatements() {
        return statements;
    }

    public void setStatements(List<SQLStatement> statements) {
        this.statements = statements;
    }

    public List<MySqlElseIfStatement> getElseIfList() {
        return elseIfList;
    }

    public void setElseIfList(List<MySqlElseIfStatement> elseIfList) {
        this.elseIfList = elseIfList;
    }

    public MySqlElseStatement getElseItem() {
        return elseItem;
    }

    public void setElseItem(MySqlElseStatement elseItem) {
        this.elseItem = elseItem;
    }

    /**
     * @author zz email:455910092@qq.com
     * @version V1.0
     * @Description: MySql procedure else if statement
     * @date 2015-9-14
     */
    public static class MySqlElseIfStatement extends MySqlObjectImpl {

        private SQLExpr condition;
        private List<SQLStatement> statements = new ArrayList<SQLStatement>();

        @Override
        public void accept0(MySqlASTVisitor visitor) {
            if (visitor.visit(this)) {
                acceptChild(visitor, condition);
                acceptChild(visitor, statements);
            }
            visitor.endVisit(this);
        }

        public SQLExpr getCondition() {
            return condition;
        }

        public void setCondition(SQLExpr condition) {
            this.condition = condition;
        }

        public List<SQLStatement> getStatements() {
            return statements;
        }

        public void setStatements(List<SQLStatement> statements) {
            this.statements = statements;
        }

    }


}
