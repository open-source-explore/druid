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

import com.alibaba.druid.sql.ast.SQLDataType;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlStatementImpl;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlASTVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zz email:455910092@qq.com
 * @version V1.0
 * @Description: MySql declare statement
 * @date 2015-9-14
 */
public class MySqlDeclareStatement extends MySqlStatementImpl {

    //var type
    private SQLDataType type;
    //var list
    private List<SQLExpr> varList = new ArrayList<SQLExpr>();


    public List<SQLExpr> getVarList() {
        return varList;
    }

    public void addVar(SQLExpr expr) {
        varList.add(expr);
    }

    public void setVarList(List<SQLExpr> varList) {
        this.varList = varList;
    }

    public SQLDataType getType() {
        return type;
    }

    public void setType(SQLDataType type) {
        this.type = type;
    }

    @Override
    public void accept0(MySqlASTVisitor visitor) {
        // TODO Auto-generated method stub
        if (visitor.visit(this)) {
            acceptChild(visitor, type);
            acceptChild(visitor, varList);
        }
        visitor.endVisit(this);

    }

}
