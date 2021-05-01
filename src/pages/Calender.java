package pages;

public enum Calender {
	DAY("days"), //
	MONTH("months"),//
	YEAR("years");
	
	private String canlenderLabel;

	private Calender(String canlenderLabel) {
		this.setCanlenderLabel(canlenderLabel);
	}

	public String getCanlenderLabel() {
		return canlenderLabel;
	}

	public void setCanlenderLabel(String canlenderLabel) {
		this.canlenderLabel = canlenderLabel;
	}
}
