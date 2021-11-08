package pe.edu.upc.SpringStartupInvest.util.startup;

import java.util.Date;

public class DateTimeUtil {
private Date startDate;
private Date endDate;

private String startDateText;
private String endDateText;
private String intervalDate;
private String categoryText;
public DateTimeUtil() {
	super();
}


public DateTimeUtil(Date startDate, Date endDate, String startDateText, String endDateText, String intervalDate,
		String categoryText) {
	super();
	this.startDate = startDate;
	this.endDate = endDate;
	this.startDateText = startDateText;
	this.endDateText = endDateText;
	this.intervalDate = intervalDate;
	this.categoryText = categoryText;
}


public String getCategoryText() {
	return categoryText;
}


public void setCategoryText(String categoryText) {
	this.categoryText = categoryText;
}


public String getIntervalDate() {
	return intervalDate;
}

public void setIntervalDate(String intervalDate) {
	this.intervalDate = intervalDate;
}

public Date getStartDate() {
	return startDate;
}

public void setStartDate(Date startDate) {
	this.startDate = startDate;
}

public Date getEndDate() {
	return endDate;
}

public void setEndDate(Date endDate) {
	this.endDate = endDate;
}

public String getStartDateText() {
	return startDateText;
}

public void setStartDateText(String startDateText) {
	this.startDateText = startDateText;
}

public String getEndDateText() {
	return endDateText;
}

public void setEndDateText(String endDateText) {
	this.endDateText = endDateText;
}





	
}
