/**
 * Copyright (c) 2015-2022 daixiao All rights reserved.
 */
package me.mutai.codegraph.model;

import java.util.List;

/**
 *
 * @author daixiao
 * @version v 0.1 2022/4/17
 */
public class CodeNode {

    private String condition;

    private List<CodeBlockNode> statements;

    private String identifier;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public List<CodeBlockNode> getStatements() {
        return statements;
    }

    public void setStatements(List<CodeBlockNode> statements) {
        this.statements = statements;
    }
}
