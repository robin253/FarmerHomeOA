package com.huike.app.report.model;

/**
 * <p class="detail">
 * 功能：服务报表实体类
 * </p>
 * @ClassName: TReportServer 
 * @version V1.0  
 * @date 2017年6月14日 
 * @author Zerlinda
 * Copyright 2015 tsou.com, Inc. All rights reserved
 */
public class TReportServer {
	
	private String [] reportServer;
	
    private Long id; // 服务报表ID

    private String title; // 服务报表标题

    private Integer employmentConsultAccpet; // 就业服务咨询受理

    private Integer employmentConsultDeal; // 就业服务咨询办结

    private Integer employmentAgentAccpet; // 就业服务代办受理

    private Integer employmentAgentDeal; // 就业服务代办办结

    private Integer employmentOthersAccpet; // 就业服务其他受理

    private Integer employmentOthersDeal; // 就业服务其他办结

    private Integer employmentNumbers; // 就业服务服务人次

    private String employmentRemark; // 就业服务备注

    private Integer businessConsultAccpet; // 创业融资咨询受理

    private Integer businessConsultDeal; // 创业融资咨询办结

    private Integer businessAgentAccpet; // 创业融资代办受理

    private Integer businessAgentDeal; // 创业融资代办办结

    private Integer businessOthersAccpet; // 创业融资其他受理

    private Integer businessOthersDeal; // 创业融资其他办结

    private Integer businessNumbers; // 创业融资服务人次

    private String businessRemark; // 创业融资备注

    private Integer lawConsultAccpet; // 法律服务咨询受理

    private Integer lawConsultDeal; // 法律服务咨询办结

    private Integer lawAgentAccpet; // 法律服务代办受理

    private Integer lawAgentDeal; // 法律服务代办办结

    private Integer lawOthersAccpet; // 法律服务其他受理

    private Integer lawOthersDeal; // 法律服务其他办结

    private Integer lawNumbers; // 法律服务服务人次

    private String lawRemark; // 法律服务备注

    private Integer policyConsultAccpet; // 政策服务咨询受理

    private Integer policyConsultDeal; // 政策服务咨询办结

    private Integer policyAgentAccpet; // 政策服务代办受理

    private Integer policyAgentDeal; // 政策服务代办办结

    private Integer policyOthersAccpet; // 政策服务其他受理

    private Integer policyOthersDeal; // 政策服务其他办结

    private Integer policyNumbers; // 政策服务服务人次

    private String policyRemark; // 政策服务备注

    private Integer projectConsultAccpet; // 项目申办咨询受理

    private Integer projectConsultDeal; // 项目申办咨询办结

    private Integer projectAgentAccpet; // 项目申办代办受理

    private Integer projectAgentDeal; // 项目申办代办办结

    private Integer projectOthersAccpet; // 项目申办其他受理

    private Integer projectOthersDeal; // 项目申办其他办结

    private Integer projectNumbers; // 项目申办服务人次

    private String projectRemark; // 项目申办备注

    private Integer professorConsultAccpet; // 专家指导咨询受理

    private Integer professorConsultDeal; // 专家指导咨询办结

    private Integer professorAgentAccpet; // 专家指导代办受理

    private Integer professorAgentDeal; // 专家指导代办办结

    private Integer professorOthersAccpet; // 专家指导其他受理

    private Integer professorOthersDeal; // 专家指导其他办结

    private Integer professorNumbers; // 专家指导人次

    private String professorRemark; // 专家指导备注

    private Integer trainConsultAccpet; // 技能培训咨询受理

    private Integer trainConsultDeal; // 技能培训咨询办结

    private Integer trainAgentAccpet; // 技能培训代办受理

    private Integer trainAgentDeal; // 技能培训代办办结

    private Integer trainOthersAccpet; // 技能培训其他受理

    private Integer trainOthersDeal; // 技能培训其他办结

    private Integer trainNumbers; // 技能培训服务人次

    private String trainRemark; // 技能培训备注

    private Integer insuranceConsultAccpet; // 保险业务咨询受理

    private Integer insuranceConsultDeal; // 保险业务咨询办结

    private Integer insuranceAgentAccpet; // 保险业务代办受理

    private Integer insuranceAgentDeal; // 保险业务代办受理

    private Integer insuranceOthersAccpet; // 保险业务其他受理

    private Integer insuranceOthersDeal; // 保险业务其他办结

    private Integer insuranceNumbers; // 保险业务服务人次

    private String insuranceRemark; // 保险业务备注

    private Integer weatherConsultAccpet; // 气象信息咨询受理

    private Integer weatherConsultDeal; // 气象信息咨询办结

    private Integer weatherAgentAccpet; // 气象信息咨询办结

    private Integer weatherAgentDeal; // 气象信息代办办结

    private Integer weatherOthersAccpet; // 气象信息其他受理

    private Integer weatherOthersDeal; // 气象信息其他办结

    private Integer weatherNumbers; // 气象信息服务人次

    private String weatherRemark; // 气象信息备注

    private Integer supplyConsultAccpet; // 供需信息咨询受理

    private Integer supplyConsultDeal; // 供需信息咨询办结

    private Integer supplyAgentAccpet; // 供需信息代办受理

    private Integer supplyAgentDeal; // 供需信息代办办结

    private Integer supplyOthersAccpet; // 供需信息其他受理

    private Integer supplyOthersDeal; // 供需信息其他办结

    private Integer supplyNumbers; // 供需信息服务人次

    private String supplyRemark; // 供需信息备注

    private Integer acountConsultAccpet; // 累积咨询受理

    private Integer acountConsultDeal; // 累积咨询办结

    private Integer acountAgentAccpet; // 累积代办受理

    private Integer acountAgentDeal; // 累积代办办结

    private Integer acountOthersAccpet; // 累积其他受理

    private Integer acountOthersDeal; // 累积其他受理

    private Integer acountNumbers; // 累积服务人次

    private String acountRemark; // 累积备注

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getEmploymentConsultAccpet() {
        return employmentConsultAccpet;
    }

    public void setEmploymentConsultAccpet(Integer employmentConsultAccpet) {
        this.employmentConsultAccpet = employmentConsultAccpet;
    }

    public Integer getEmploymentConsultDeal() {
        return employmentConsultDeal;
    }

    public void setEmploymentConsultDeal(Integer employmentConsultDeal) {
        this.employmentConsultDeal = employmentConsultDeal;
    }

    public Integer getEmploymentAgentAccpet() {
        return employmentAgentAccpet;
    }

    public void setEmploymentAgentAccpet(Integer employmentAgentAccpet) {
        this.employmentAgentAccpet = employmentAgentAccpet;
    }

    public Integer getEmploymentAgentDeal() {
        return employmentAgentDeal;
    }

    public void setEmploymentAgentDeal(Integer employmentAgentDeal) {
        this.employmentAgentDeal = employmentAgentDeal;
    }

    public Integer getEmploymentOthersAccpet() {
        return employmentOthersAccpet;
    }

    public void setEmploymentOthersAccpet(Integer employmentOthersAccpet) {
        this.employmentOthersAccpet = employmentOthersAccpet;
    }

    public Integer getEmploymentOthersDeal() {
        return employmentOthersDeal;
    }

    public void setEmploymentOthersDeal(Integer employmentOthersDeal) {
        this.employmentOthersDeal = employmentOthersDeal;
    }

    public Integer getEmploymentNumbers() {
        return employmentNumbers;
    }

    public void setEmploymentNumbers(Integer employmentNumbers) {
        this.employmentNumbers = employmentNumbers;
    }

    public String getEmploymentRemark() {
        return employmentRemark;
    }

    public void setEmploymentRemark(String employmentRemark) {
        this.employmentRemark = employmentRemark;
    }

    public Integer getBusinessConsultAccpet() {
        return businessConsultAccpet;
    }

    public void setBusinessConsultAccpet(Integer businessConsultAccpet) {
        this.businessConsultAccpet = businessConsultAccpet;
    }

    public Integer getBusinessConsultDeal() {
        return businessConsultDeal;
    }

    public void setBusinessConsultDeal(Integer businessConsultDeal) {
        this.businessConsultDeal = businessConsultDeal;
    }

    public Integer getBusinessAgentAccpet() {
        return businessAgentAccpet;
    }

    public void setBusinessAgentAccpet(Integer businessAgentAccpet) {
        this.businessAgentAccpet = businessAgentAccpet;
    }

    public Integer getBusinessAgentDeal() {
        return businessAgentDeal;
    }

    public void setBusinessAgentDeal(Integer businessAgentDeal) {
        this.businessAgentDeal = businessAgentDeal;
    }

    public Integer getBusinessOthersAccpet() {
        return businessOthersAccpet;
    }

    public void setBusinessOthersAccpet(Integer businessOthersAccpet) {
        this.businessOthersAccpet = businessOthersAccpet;
    }

    public Integer getBusinessOthersDeal() {
        return businessOthersDeal;
    }

    public void setBusinessOthersDeal(Integer businessOthersDeal) {
        this.businessOthersDeal = businessOthersDeal;
    }

    public Integer getBusinessNumbers() {
        return businessNumbers;
    }

    public void setBusinessNumbers(Integer businessNumbers) {
        this.businessNumbers = businessNumbers;
    }

    public String getBusinessRemark() {
        return businessRemark;
    }

    public void setBusinessRemark(String businessRemark) {
        this.businessRemark = businessRemark;
    }

    public Integer getLawConsultAccpet() {
        return lawConsultAccpet;
    }

    public void setLawConsultAccpet(Integer lawConsultAccpet) {
        this.lawConsultAccpet = lawConsultAccpet;
    }

    public Integer getLawConsultDeal() {
        return lawConsultDeal;
    }

    public void setLawConsultDeal(Integer lawConsultDeal) {
        this.lawConsultDeal = lawConsultDeal;
    }

    public Integer getLawAgentAccpet() {
        return lawAgentAccpet;
    }

    public void setLawAgentAccpet(Integer lawAgentAccpet) {
        this.lawAgentAccpet = lawAgentAccpet;
    }

    public Integer getLawAgentDeal() {
        return lawAgentDeal;
    }

    public void setLawAgentDeal(Integer lawAgentDeal) {
        this.lawAgentDeal = lawAgentDeal;
    }

    public Integer getLawOthersAccpet() {
        return lawOthersAccpet;
    }

    public void setLawOthersAccpet(Integer lawOthersAccpet) {
        this.lawOthersAccpet = lawOthersAccpet;
    }

    public Integer getLawOthersDeal() {
        return lawOthersDeal;
    }

    public void setLawOthersDeal(Integer lawOthersDeal) {
        this.lawOthersDeal = lawOthersDeal;
    }

    public Integer getLawNumbers() {
        return lawNumbers;
    }

    public void setLawNumbers(Integer lawNumbers) {
        this.lawNumbers = lawNumbers;
    }

    public String getLawRemark() {
        return lawRemark;
    }

    public void setLawRemark(String lawRemark) {
        this.lawRemark = lawRemark;
    }

    public Integer getPolicyConsultAccpet() {
        return policyConsultAccpet;
    }

    public void setPolicyConsultAccpet(Integer policyConsultAccpet) {
        this.policyConsultAccpet = policyConsultAccpet;
    }

    public Integer getPolicyConsultDeal() {
        return policyConsultDeal;
    }

    public void setPolicyConsultDeal(Integer policyConsultDeal) {
        this.policyConsultDeal = policyConsultDeal;
    }

    public Integer getPolicyAgentAccpet() {
        return policyAgentAccpet;
    }

    public void setPolicyAgentAccpet(Integer policyAgentAccpet) {
        this.policyAgentAccpet = policyAgentAccpet;
    }

    public Integer getPolicyAgentDeal() {
        return policyAgentDeal;
    }

    public void setPolicyAgentDeal(Integer policyAgentDeal) {
        this.policyAgentDeal = policyAgentDeal;
    }

    public Integer getPolicyOthersAccpet() {
        return policyOthersAccpet;
    }

    public void setPolicyOthersAccpet(Integer policyOthersAccpet) {
        this.policyOthersAccpet = policyOthersAccpet;
    }

    public Integer getPolicyOthersDeal() {
        return policyOthersDeal;
    }

    public void setPolicyOthersDeal(Integer policyOthersDeal) {
        this.policyOthersDeal = policyOthersDeal;
    }

    public Integer getPolicyNumbers() {
        return policyNumbers;
    }

    public void setPolicyNumbers(Integer policyNumbers) {
        this.policyNumbers = policyNumbers;
    }

    public String getPolicyRemark() {
        return policyRemark;
    }

    public void setPolicyRemark(String policyRemark) {
        this.policyRemark = policyRemark;
    }

    public Integer getProjectConsultAccpet() {
        return projectConsultAccpet;
    }

    public void setProjectConsultAccpet(Integer projectConsultAccpet) {
        this.projectConsultAccpet = projectConsultAccpet;
    }

    public Integer getProjectConsultDeal() {
        return projectConsultDeal;
    }

    public void setProjectConsultDeal(Integer projectConsultDeal) {
        this.projectConsultDeal = projectConsultDeal;
    }

    public Integer getProjectAgentAccpet() {
        return projectAgentAccpet;
    }

    public void setProjectAgentAccpet(Integer projectAgentAccpet) {
        this.projectAgentAccpet = projectAgentAccpet;
    }

    public Integer getProjectAgentDeal() {
        return projectAgentDeal;
    }

    public void setProjectAgentDeal(Integer projectAgentDeal) {
        this.projectAgentDeal = projectAgentDeal;
    }

    public Integer getProjectOthersAccpet() {
        return projectOthersAccpet;
    }

    public void setProjectOthersAccpet(Integer projectOthersAccpet) {
        this.projectOthersAccpet = projectOthersAccpet;
    }

    public Integer getProjectOthersDeal() {
        return projectOthersDeal;
    }

    public void setProjectOthersDeal(Integer projectOthersDeal) {
        this.projectOthersDeal = projectOthersDeal;
    }

    public Integer getProjectNumbers() {
        return projectNumbers;
    }

    public void setProjectNumbers(Integer projectNumbers) {
        this.projectNumbers = projectNumbers;
    }

    public String getProjectRemark() {
        return projectRemark;
    }

    public void setProjectRemark(String projectRemark) {
        this.projectRemark = projectRemark;
    }

    public Integer getProfessorConsultAccpet() {
        return professorConsultAccpet;
    }

    public void setProfessorConsultAccpet(Integer professorConsultAccpet) {
        this.professorConsultAccpet = professorConsultAccpet;
    }

    public Integer getProfessorConsultDeal() {
        return professorConsultDeal;
    }

    public void setProfessorConsultDeal(Integer professorConsultDeal) {
        this.professorConsultDeal = professorConsultDeal;
    }

    public Integer getProfessorAgentAccpet() {
        return professorAgentAccpet;
    }

    public void setProfessorAgentAccpet(Integer professorAgentAccpet) {
        this.professorAgentAccpet = professorAgentAccpet;
    }

    public Integer getProfessorAgentDeal() {
        return professorAgentDeal;
    }

    public void setProfessorAgentDeal(Integer professorAgentDeal) {
        this.professorAgentDeal = professorAgentDeal;
    }

    public Integer getProfessorOthersAccpet() {
        return professorOthersAccpet;
    }

    public void setProfessorOthersAccpet(Integer professorOthersAccpet) {
        this.professorOthersAccpet = professorOthersAccpet;
    }

    public Integer getProfessorOthersDeal() {
        return professorOthersDeal;
    }

    public void setProfessorOthersDeal(Integer professorOthersDeal) {
        this.professorOthersDeal = professorOthersDeal;
    }

    public Integer getProfessorNumbers() {
        return professorNumbers;
    }

    public void setProfessorNumbers(Integer professorNumbers) {
        this.professorNumbers = professorNumbers;
    }

    public String getProfessorRemark() {
        return professorRemark;
    }

    public void setProfessorRemark(String professorRemark) {
        this.professorRemark = professorRemark;
    }

    public Integer getTrainConsultAccpet() {
        return trainConsultAccpet;
    }

    public void setTrainConsultAccpet(Integer trainConsultAccpet) {
        this.trainConsultAccpet = trainConsultAccpet;
    }

    public Integer getTrainConsultDeal() {
        return trainConsultDeal;
    }

    public void setTrainConsultDeal(Integer trainConsultDeal) {
        this.trainConsultDeal = trainConsultDeal;
    }

    public Integer getTrainAgentAccpet() {
        return trainAgentAccpet;
    }

    public void setTrainAgentAccpet(Integer trainAgentAccpet) {
        this.trainAgentAccpet = trainAgentAccpet;
    }

    public Integer getTrainAgentDeal() {
        return trainAgentDeal;
    }

    public void setTrainAgentDeal(Integer trainAgentDeal) {
        this.trainAgentDeal = trainAgentDeal;
    }

    public Integer getTrainOthersAccpet() {
        return trainOthersAccpet;
    }

    public void setTrainOthersAccpet(Integer trainOthersAccpet) {
        this.trainOthersAccpet = trainOthersAccpet;
    }

    public Integer getTrainOthersDeal() {
        return trainOthersDeal;
    }

    public void setTrainOthersDeal(Integer trainOthersDeal) {
        this.trainOthersDeal = trainOthersDeal;
    }

    public Integer getTrainNumbers() {
        return trainNumbers;
    }

    public void setTrainNumbers(Integer trainNumbers) {
        this.trainNumbers = trainNumbers;
    }

    public String getTrainRemark() {
        return trainRemark;
    }

    public void setTrainRemark(String trainRemark) {
        this.trainRemark = trainRemark;
    }

    public Integer getInsuranceConsultAccpet() {
        return insuranceConsultAccpet;
    }

    public void setInsuranceConsultAccpet(Integer insuranceConsultAccpet) {
        this.insuranceConsultAccpet = insuranceConsultAccpet;
    }

    public Integer getInsuranceConsultDeal() {
        return insuranceConsultDeal;
    }

    public void setInsuranceConsultDeal(Integer insuranceConsultDeal) {
        this.insuranceConsultDeal = insuranceConsultDeal;
    }

    public Integer getInsuranceAgentAccpet() {
        return insuranceAgentAccpet;
    }

    public void setInsuranceAgentAccpet(Integer insuranceAgentAccpet) {
        this.insuranceAgentAccpet = insuranceAgentAccpet;
    }

    public Integer getInsuranceAgentDeal() {
        return insuranceAgentDeal;
    }

    public void setInsuranceAgentDeal(Integer insuranceAgentDeal) {
        this.insuranceAgentDeal = insuranceAgentDeal;
    }

    public Integer getInsuranceOthersAccpet() {
        return insuranceOthersAccpet;
    }

    public void setInsuranceOthersAccpet(Integer insuranceOthersAccpet) {
        this.insuranceOthersAccpet = insuranceOthersAccpet;
    }

    public Integer getInsuranceOthersDeal() {
        return insuranceOthersDeal;
    }

    public void setInsuranceOthersDeal(Integer insuranceOthersDeal) {
        this.insuranceOthersDeal = insuranceOthersDeal;
    }

    public Integer getInsuranceNumbers() {
        return insuranceNumbers;
    }

    public void setInsuranceNumbers(Integer insuranceNumbers) {
        this.insuranceNumbers = insuranceNumbers;
    }

    public String getInsuranceRemark() {
        return insuranceRemark;
    }

    public void setInsuranceRemark(String insuranceRemark) {
        this.insuranceRemark = insuranceRemark;
    }

    public Integer getWeatherConsultAccpet() {
        return weatherConsultAccpet;
    }

    public void setWeatherConsultAccpet(Integer weatherConsultAccpet) {
        this.weatherConsultAccpet = weatherConsultAccpet;
    }

    public Integer getWeatherConsultDeal() {
        return weatherConsultDeal;
    }

    public void setWeatherConsultDeal(Integer weatherConsultDeal) {
        this.weatherConsultDeal = weatherConsultDeal;
    }

    public Integer getWeatherAgentAccpet() {
        return weatherAgentAccpet;
    }

    public void setWeatherAgentAccpet(Integer weatherAgentAccpet) {
        this.weatherAgentAccpet = weatherAgentAccpet;
    }

    public Integer getWeatherAgentDeal() {
        return weatherAgentDeal;
    }

    public void setWeatherAgentDeal(Integer weatherAgentDeal) {
        this.weatherAgentDeal = weatherAgentDeal;
    }

    public Integer getWeatherOthersAccpet() {
        return weatherOthersAccpet;
    }

    public void setWeatherOthersAccpet(Integer weatherOthersAccpet) {
        this.weatherOthersAccpet = weatherOthersAccpet;
    }

    public Integer getWeatherOthersDeal() {
        return weatherOthersDeal;
    }

    public void setWeatherOthersDeal(Integer weatherOthersDeal) {
        this.weatherOthersDeal = weatherOthersDeal;
    }

    public Integer getWeatherNumbers() {
        return weatherNumbers;
    }

    public void setWeatherNumbers(Integer weatherNumbers) {
        this.weatherNumbers = weatherNumbers;
    }

    public String getWeatherRemark() {
        return weatherRemark;
    }

    public void setWeatherRemark(String weatherRemark) {
        this.weatherRemark = weatherRemark;
    }

    public Integer getSupplyConsultAccpet() {
        return supplyConsultAccpet;
    }

    public void setSupplyConsultAccpet(Integer supplyConsultAccpet) {
        this.supplyConsultAccpet = supplyConsultAccpet;
    }

    public Integer getSupplyConsultDeal() {
        return supplyConsultDeal;
    }

    public void setSupplyConsultDeal(Integer supplyConsultDeal) {
        this.supplyConsultDeal = supplyConsultDeal;
    }

    public Integer getSupplyAgentAccpet() {
        return supplyAgentAccpet;
    }

    public void setSupplyAgentAccpet(Integer supplyAgentAccpet) {
        this.supplyAgentAccpet = supplyAgentAccpet;
    }

    public Integer getSupplyAgentDeal() {
        return supplyAgentDeal;
    }

    public void setSupplyAgentDeal(Integer supplyAgentDeal) {
        this.supplyAgentDeal = supplyAgentDeal;
    }

    public Integer getSupplyOthersAccpet() {
        return supplyOthersAccpet;
    }

    public void setSupplyOthersAccpet(Integer supplyOthersAccpet) {
        this.supplyOthersAccpet = supplyOthersAccpet;
    }

    public Integer getSupplyOthersDeal() {
        return supplyOthersDeal;
    }

    public void setSupplyOthersDeal(Integer supplyOthersDeal) {
        this.supplyOthersDeal = supplyOthersDeal;
    }

    public Integer getSupplyNumbers() {
        return supplyNumbers;
    }

    public void setSupplyNumbers(Integer supplyNumbers) {
        this.supplyNumbers = supplyNumbers;
    }

    public String getSupplyRemark() {
        return supplyRemark;
    }

    public void setSupplyRemark(String supplyRemark) {
        this.supplyRemark = supplyRemark;
    }

    public Integer getAcountConsultAccpet() {
        return acountConsultAccpet;
    }

    public void setAcountConsultAccpet(Integer acountConsultAccpet) {
        this.acountConsultAccpet = acountConsultAccpet;
    }

    public Integer getAcountConsultDeal() {
        return acountConsultDeal;
    }

    public void setAcountConsultDeal(Integer acountConsultDeal) {
        this.acountConsultDeal = acountConsultDeal;
    }

    public Integer getAcountAgentAccpet() {
        return acountAgentAccpet;
    }

    public void setAcountAgentAccpet(Integer acountAgentAccpet) {
        this.acountAgentAccpet = acountAgentAccpet;
    }

    public Integer getAcountAgentDeal() {
        return acountAgentDeal;
    }

    public void setAcountAgentDeal(Integer acountAgentDeal) {
        this.acountAgentDeal = acountAgentDeal;
    }

    public Integer getAcountOthersAccpet() {
        return acountOthersAccpet;
    }

    public void setAcountOthersAccpet(Integer acountOthersAccpet) {
        this.acountOthersAccpet = acountOthersAccpet;
    }

    public Integer getAcountOthersDeal() {
        return acountOthersDeal;
    }

    public void setAcountOthersDeal(Integer acountOthersDeal) {
        this.acountOthersDeal = acountOthersDeal;
    }

    public Integer getAcountNumbers() {
        return acountNumbers;
    }

    public void setAcountNumbers(Integer acountNumbers) {
        this.acountNumbers = acountNumbers;
    }

    public String getAcountRemark() {
        return acountRemark;
    }

    public void setAcountRemark(String acountRemark) {
        this.acountRemark = acountRemark;
    }

	public String[] getReportServer() {
		return reportServer;
	}

	public void setReportServer(String[] reportServer) {
		this.reportServer = reportServer;
	}
    
}