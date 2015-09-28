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
package com.alibaba.druid.sql.dialect.oracle.ast.stmt;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.dialect.oracle.ast.OracleSQLObjectImpl;
import com.alibaba.druid.sql.dialect.oracle.visitor.OracleASTVisitor;

public class OracleSelectHierachicalQueryClause extends OracleSQLObjectImpl {

    private SQLExpr startWith;
    private SQLExpr connectBy;
    private boolean prior = false;
    private boolean noCycle = false;

    public OracleSelectHierachicalQueryClause() {

    }

    public boolean isPrior() {
        return prior;
    }

    public void setPrior(boolean prior) {
        this.prior = prior;
    }

    public void accept0(OracleASTVisitor visitor) {
        if (visitor.visit(this)) {
            acceptChild(visitor, this.startWith);
            acceptChild(visitor, this.connectBy);
        }

        visitor.endVisit(this);
    }

    public SQLExpr getStartWith() {
        return this.startWith;
    }

    public void setStartWith(SQLExpr startWith) {
        this.startWith = startWith;
    }

    public SQLExpr getConnectBy() {
        return this.connectBy;
    }

    public void setConnectBy(SQLExpr connectBy) {
        this.connectBy = connectBy;
    }

    public boolean isNoCycle() {
        return this.noCycle;
    }

    public void setNoCycle(boolean noCycle) {
        this.noCycle = noCycle;
    }
}
