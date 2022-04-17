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
public abstract class AbstractCodeBlock {

    protected String classifier;

    protected Long timestamp;

    public String getClassifier() {
        return classifier;
    }

    public void setClassifier(String classifier) {
        this.classifier = classifier;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
