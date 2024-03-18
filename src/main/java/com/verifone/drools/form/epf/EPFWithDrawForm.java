package com.verifone.drools.form.epf;

public class EPFWithDrawForm {

   private String purpose;

   private Integer yearsOfService;

   private Double maxAmountAllowed;

   private Integer timesAllowed;

   private String remarks;

   public String getPurpose() {
      return purpose;
   }

   public void setPurpose(String purpose) {
      this.purpose = purpose;
   }

   public Integer getYearsOfService() {
      return yearsOfService;
   }

   public void setYearsOfService(Integer yearsOfService) {
      this.yearsOfService = yearsOfService;
   }

   public Double getMaxAmountAllowed() {
      return maxAmountAllowed;
   }

   public void setMaxAmountAllowed(Double maxAmountAllowed) {
      this.maxAmountAllowed = maxAmountAllowed;
   }

   public Integer getTimesAllowed() {
      return timesAllowed;
   }

   public void setTimesAllowed(Integer timesAllowed) {
      this.timesAllowed = timesAllowed;
   }

   public String getRemarks() {
      return remarks;
   }

   public void setRemarks(String remarks) {
      this.remarks = remarks;
   }

}
