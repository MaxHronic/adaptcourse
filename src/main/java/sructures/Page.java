package sructures;

/**
 * Created by Max on 26.02.2017.
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Page", propOrder = {
        "pageId",
        "topicId",
        "in_topicId",
        "text",
        "extText"
})
public class Page {
    private static final Logger LOG = LogManager.getLogger(lists.TopicPages.class);
    @XmlElement(name = "pageId")
    private long pageId;
    @XmlElement(name = "topicId")
    private long topicId;
    @XmlElement(name = "in_topicId")
    private long in_topicId;
    @XmlElement(name = "text")
    private String text;
    @XmlElement(name = "extText")
    private String extText;


    public long getPageId() {
        return pageId;
    }

    public void setPageId(long pageId) {
        this.pageId = pageId;
    }

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public long getIn_topicId() {
        return in_topicId;
    }

    public void setIn_topicId(long in_topicId) {
        this.in_topicId = in_topicId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getExtText() {
        return extText;
    }

    public void setExtText(String extText) {
        this.extText = extText;
    }


}

