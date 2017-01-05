package com.angkasa.model;

import com.google.gson.annotations.Expose;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author <a href="mailto:hazlan@gmail.com">Hazlan Rozaimi</a>
 */
@Entity
@Table(name = "event")
@Indexed
@XmlRootElement
public class Event extends BaseObject implements Serializable {
    private static final long serialVersionUID = 3832626162173359416L;

    @Expose
    private Long id;
    @Expose
    private String eventCode;
    @Expose
    private String name;
    @Expose
    private String description;

    @Expose
    private String drawResult;

    @Expose
    private Date startTime;
    @Expose
    private Date endTime;
    @Expose
    private BigDecimal geoLat;
    @Expose
    private BigDecimal geoLong;

    public Event() {

    }

    @Override
    @Transient
    public String getPrimaryKey() {
        return getStringKey(this.id);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "event_code", nullable = false, length = 50)
    @Field
    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    @Column(name = "name", nullable = false, length = 200)
    @Field
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", nullable = true, length = 1000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "draw_result", nullable = true, length = 1000)
    public String getDrawResult() {
        return drawResult;
    }

    public void setDrawResult(String drawResult) {
        this.drawResult = drawResult;
    }

    @Column(name = "start_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Column(name = "end_time", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Column(name = "geo_long", nullable = true)
    public BigDecimal getGeoLong() {
        return geoLong;
    }

    public void setGeoLong(BigDecimal geoLong) {
        this.geoLong = geoLong;
    }

    @Column(name = "geo_lat", nullable = true)
    public BigDecimal getGeoLat() {
        return geoLat;
    }

    public void setGeoLat(BigDecimal geoLat) {
        this.geoLat = geoLat;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", eventCode='" + eventCode + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", drawResult='" + drawResult + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", geoLat=" + geoLat +
                ", geoLong=" + geoLong +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (description != null ? !description.equals(event.description) : event.description != null) return false;
        if (drawResult != null ? !drawResult.equals(event.drawResult) : event.drawResult != null) return false;
        if (endTime != null ? !endTime.equals(event.endTime) : event.endTime != null) return false;
        if (eventCode != null ? !eventCode.equals(event.eventCode) : event.eventCode != null) return false;
        if (geoLat != null ? !geoLat.equals(event.geoLat) : event.geoLat != null) return false;
        if (geoLong != null ? !geoLong.equals(event.geoLong) : event.geoLong != null) return false;
        if (id != null ? !id.equals(event.id) : event.id != null) return false;
        if (name != null ? !name.equals(event.name) : event.name != null) return false;
        if (startTime != null ? !startTime.equals(event.startTime) : event.startTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (eventCode != null ? eventCode.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (drawResult != null ? drawResult.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (geoLat != null ? geoLat.hashCode() : 0);
        result = 31 * result + (geoLong != null ? geoLong.hashCode() : 0);
        return result;
    }
}
