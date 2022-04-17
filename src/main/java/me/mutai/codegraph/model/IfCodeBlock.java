/**
 * Copyright (c) 2015-2022 daixiao All rights reserved.
 */
package me.mutai.codegraph.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daixiao
 * @version v 0.1 2022/4/17
 */
public class IfCodeBlock extends AbstractCodeBlock {

    private String classifier = "if";

    private List<CodeNode> codeNodes = new ArrayList<>();

    public List<CodeNode> getCodeNodes() {
        return codeNodes;
    }

    public void setCodeNodes(List<CodeNode> codeNodes) {
        this.codeNodes = codeNodes;
    }
}
