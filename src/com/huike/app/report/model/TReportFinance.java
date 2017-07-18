package com.huike.app.report.model;

/**
 * <p class="detail">
 * 功能：财务报表实体信息类
 * </p>
 * @ClassName: TReportFinance 
 * @version V1.0  
 * @date 2017年6月7日 
 * @author Zerlinda
 * Copyright 2015 tsou.com, Inc. All rights reserved
 */
public class TReportFinance {
	
	private String [] reportFinance; // 财务报表数据集合
	
    private Long id; // 财务报表ID

    private String title; // 报表名

    private String hbzjBegin; // 货币资金年初数

    private String hbzjEnd; // 货币资金期末数

    private String dqtzBegin; // 短期投资年初数

    private String dqtzEnd; // 短期投资期末数

    private String yskxBegin; // 应收款项年初数

    private String yskxEnd; // 应收款项期末数

    private String yfzkBegin; // 预付账款年初数

    private String yfzkEnd; // 预付账款期末数

    private String chBegin; // 存货年初数

    private String chEnd; // 存货期末数

    private String dtfyBegin; // 待摊费用年初数

    private String dtfyEnd; // 待摊费用期末数

    private String ynndqdcqzqtzBegin; // 一年内到期的长期债权投资年初数

    private String ynndqdcqzqtzEnd; // 一年内到期的长期债权投资期末数

    private String qtldzcBegin; // 其他流动资产年初数

    private String qtldzcEnd; // 其他流动资产期末数

    private String ldzchjBegin; // 流动资产合计年初数

    private String ldzchjEnd; // 流动资产合计期末数

    private String cqgqtzBegin; // 长期股权投资年初数

    private String cqgqtzEnd; // 长期股权投资期末数

    private String cqzqtzBegin; // 长期债权投资年初数

    private String cqzqtzEnd; // 长期债权投资期末数

    private String cqtzhjBegin; // 长期投资合计年初数

    private String cqtzhjEnd; // 长期投资合计期末数

    private String gdzcyjBegin; // 固定资产原价年初数

    private String gdzcyjEnd; // 固定资产原件期末数

    private String ljzjBegin; // 减：累积折旧年初数

    private String ljzjEnd; // 减：累积折旧期末数

    private String gdzcjzBegin; // 固定资产净值年初数

    private String gdzcjzEnd; // 固定资产净值期末数

    private String zjgcBegin; // 在建工程年初数

    private String zjgcEnd; // 在建工程期末数

    private String wwwhzcBegin; // 文物文化资产年初数

    private String wwwhzcEnd; // 文物文化资产期末数

    private String gdzcqlBegin; // 固定资产清理年初数

    private String gdzcqlEnd; // 固定资产清理期末数

    private String gdzchjBegin; // 固定资产合计年初数

    private String gdzchjEnd; // 固定资产合计期末数

    private String wxzcBegin; // 无形资产年初数

    private String wxzcEnd; // 无形资产期末数

    private String stdlzcBegin; // 受托代理资产年初数

    private String stdlzcEnd; // 受托代理资产期末数

    private String zczjBegin; // 资产总计年初数

    private String zczjEnd; // 资产总计期末数

    private String dqjdBegin; // 短期借款年初数

    private String dqjdEnd; // 短期借款期末数

    private String yfkxBegin; // 应付款项年初数

    private String yfkxEnd; // 应付款项期末数
    
    private String yfgzBegin; // 应付工资年初数

    private String yfgzEnd; // 应付工资期末数

    private String yjsjBegin;  // 应交税金年初数

    private String yjsjEnd; // 应交税金期末数

    private String yszkBegin; // 预收账款年初数

    private String yszkEnd; // 预收账款期末数

    private String ytfyBegin; // 预提费用年初数

    private String ytfyEnd; // 预提费用期末数

    private String yjfzBegin; // 预计负债年初数

    private String yjfzEnd; // 预计负债期末数

    private String ynndqdcqfzBegin; // 一年内到期的长期负债年初数

    private String ynndqdcqfzEnd; // 一年内到期的长期负债期末数

    private String qtldfzBegin;  // 其他流动负债年初数

    private String qtldfzEnd; // 其他流动负债期末数

    private String ldfzhjBegin; // 流动负债合计年初数

    private String ldfzhjEnd;  // 流动负债合计期末数

    private String cqjdBegin; // 长期借款年初数

    private String cqjdEnd; // 长期借款期末数

    private String cqyfkBegin; // 长期应付款年初数

    private String cqyfkEnd;  // 长期应付款期末数

    private String qtcqfzBegin; // 其他长期负债年初数

    private String qtcqfzEnd; // 其他长期负债期末数

    private String cqfzhjBegin; // 长期负债合计年初数

    private String cqfzhjEnd; // 长期负债合计期末数

    private String stdlfzBegin; // 受托代理负债年初数

    private String stdlfzEnd; // 受托代理负债期末数

    private String fzhjBegin; // 负债合计年初数

    private String fzhjEnd; // 负债合计期末数

    private String fxdxjzcBegin; // 非限定性净资产年初数

    private String fxdxjzcEnd; // 非限定性净资产期末数

    private String xdxjzcBegin; // 限定性净资产年初数

    private String xdxjzcEnd; // 限定性净资产期末数

    private String jzchjBegin; // 净资产合计年初数

    private String jzchjEnd; // 净资产合计期末数

    private String fzhjzchjBegin; // 负债和净资产合计年初数

    private String fzhjzchjEnd; // 负债和净资产合计期末数

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

    public String getHbzjBegin() {
        return hbzjBegin;
    }

    public void setHbzjBegin(String hbzjBegin) {
        this.hbzjBegin = hbzjBegin;
    }

    public String getHbzjEnd() {
        return hbzjEnd;
    }

    public void setHbzjEnd(String hbzjEnd) {
        this.hbzjEnd = hbzjEnd;
    }

    public String getDqtzBegin() {
        return dqtzBegin;
    }

    public void setDqtzBegin(String dqtzBegin) {
        this.dqtzBegin = dqtzBegin;
    }

    public String getDqtzEnd() {
        return dqtzEnd;
    }

    public void setDqtzEnd(String dqtzEnd) {
        this.dqtzEnd = dqtzEnd;
    }

    public String getYskxBegin() {
        return yskxBegin;
    }

    public void setYskxBegin(String yskxBegin) {
        this.yskxBegin = yskxBegin;
    }

    public String getYskxEnd() {
        return yskxEnd;
    }

    public void setYskxEnd(String yskxEnd) {
        this.yskxEnd = yskxEnd;
    }

    public String getYfzkBegin() {
        return yfzkBegin;
    }

    public void setYfzkBegin(String yfzkBegin) {
        this.yfzkBegin = yfzkBegin;
    }

    public String getYfzkEnd() {
        return yfzkEnd;
    }

    public void setYfzkEnd(String yfzkEnd) {
        this.yfzkEnd = yfzkEnd;
    }

    public String getChBegin() {
        return chBegin;
    }

    public void setChBegin(String chBegin) {
        this.chBegin = chBegin;
    }

    public String getChEnd() {
        return chEnd;
    }

    public void setChEnd(String chEnd) {
        this.chEnd = chEnd;
    }

    public String getDtfyBegin() {
        return dtfyBegin;
    }

    public void setDtfyBegin(String dtfyBegin) {
        this.dtfyBegin = dtfyBegin;
    }

    public String getDtfyEnd() {
        return dtfyEnd;
    }

    public void setDtfyEnd(String dtfyEnd) {
        this.dtfyEnd = dtfyEnd;
    }

    public String getYnndqdcqzqtzBegin() {
        return ynndqdcqzqtzBegin;
    }

    public void setYnndqdcqzqtzBegin(String ynndqdcqzqtzBegin) {
        this.ynndqdcqzqtzBegin = ynndqdcqzqtzBegin;
    }

    public String getYnndqdcqzqtzEnd() {
        return ynndqdcqzqtzEnd;
    }

    public void setYnndqdcqzqtzEnd(String ynndqdcqzqtzEnd) {
        this.ynndqdcqzqtzEnd = ynndqdcqzqtzEnd;
    }

    public String getQtldzcBegin() {
        return qtldzcBegin;
    }

    public void setQtldzcBegin(String qtldzcBegin) {
        this.qtldzcBegin = qtldzcBegin;
    }

    public String getQtldzcEnd() {
        return qtldzcEnd;
    }

    public void setQtldzcEnd(String qtldzcEnd) {
        this.qtldzcEnd = qtldzcEnd;
    }

    public String getLdzchjBegin() {
        return ldzchjBegin;
    }

    public void setLdzchjBegin(String ldzchjBegin) {
        this.ldzchjBegin = ldzchjBegin;
    }

    public String getLdzchjEnd() {
        return ldzchjEnd;
    }

    public void setLdzchjEnd(String ldzchjEnd) {
        this.ldzchjEnd = ldzchjEnd;
    }

    public String getCqgqtzBegin() {
        return cqgqtzBegin;
    }

    public void setCqgqtzBegin(String cqgqtzBegin) {
        this.cqgqtzBegin = cqgqtzBegin;
    }

    public String getCqgqtzEnd() {
        return cqgqtzEnd;
    }

    public void setCqgqtzEnd(String cqgqtzEnd) {
        this.cqgqtzEnd = cqgqtzEnd;
    }

    public String getCqzqtzBegin() {
        return cqzqtzBegin;
    }

    public void setCqzqtzBegin(String cqzqtzBegin) {
        this.cqzqtzBegin = cqzqtzBegin;
    }

    public String getCqzqtzEnd() {
        return cqzqtzEnd;
    }

    public void setCqzqtzEnd(String cqzqtzEnd) {
        this.cqzqtzEnd = cqzqtzEnd;
    }

    public String getCqtzhjBegin() {
        return cqtzhjBegin;
    }

    public void setCqtzhjBegin(String cqtzhjBegin) {
        this.cqtzhjBegin = cqtzhjBegin;
    }

    public String getCqtzhjEnd() {
        return cqtzhjEnd;
    }

    public void setCqtzhjEnd(String cqtzhjEnd) {
        this.cqtzhjEnd = cqtzhjEnd;
    }

    public String getGdzcyjBegin() {
        return gdzcyjBegin;
    }

    public void setGdzcyjBegin(String gdzcyjBegin) {
        this.gdzcyjBegin = gdzcyjBegin;
    }

    public String getGdzcyjEnd() {
        return gdzcyjEnd;
    }

    public void setGdzcyjEnd(String gdzcyjEnd) {
        this.gdzcyjEnd = gdzcyjEnd;
    }

    public String getLjzjBegin() {
        return ljzjBegin;
    }

    public void setLjzjBegin(String ljzjBegin) {
        this.ljzjBegin = ljzjBegin;
    }

    public String getLjzjEnd() {
        return ljzjEnd;
    }

    public void setLjzjEnd(String ljzjEnd) {
        this.ljzjEnd = ljzjEnd;
    }

    public String getGdzcjzBegin() {
        return gdzcjzBegin;
    }

    public void setGdzcjzBegin(String gdzcjzBegin) {
        this.gdzcjzBegin = gdzcjzBegin;
    }

    public String getGdzcjzEnd() {
        return gdzcjzEnd;
    }

    public void setGdzcjzEnd(String gdzcjzEnd) {
        this.gdzcjzEnd = gdzcjzEnd;
    }

    public String getZjgcBegin() {
        return zjgcBegin;
    }

    public void setZjgcBegin(String zjgcBegin) {
        this.zjgcBegin = zjgcBegin;
    }

    public String getZjgcEnd() {
        return zjgcEnd;
    }

    public void setZjgcEnd(String zjgcEnd) {
        this.zjgcEnd = zjgcEnd;
    }

    public String getWwwhzcBegin() {
        return wwwhzcBegin;
    }

    public void setWwwhzcBegin(String wwwhzcBegin) {
        this.wwwhzcBegin = wwwhzcBegin;
    }

    public String getWwwhzcEnd() {
        return wwwhzcEnd;
    }

    public void setWwwhzcEnd(String wwwhzcEnd) {
        this.wwwhzcEnd = wwwhzcEnd;
    }

    public String getGdzcqlBegin() {
        return gdzcqlBegin;
    }

    public void setGdzcqlBegin(String gdzcqlBegin) {
        this.gdzcqlBegin = gdzcqlBegin;
    }

    public String getGdzcqlEnd() {
        return gdzcqlEnd;
    }

    public void setGdzcqlEnd(String gdzcqlEnd) {
        this.gdzcqlEnd = gdzcqlEnd;
    }

    public String getGdzchjBegin() {
        return gdzchjBegin;
    }

    public void setGdzchjBegin(String gdzchjBegin) {
        this.gdzchjBegin = gdzchjBegin;
    }

    public String getGdzchjEnd() {
        return gdzchjEnd;
    }

    public void setGdzchjEnd(String gdzchjEnd) {
        this.gdzchjEnd = gdzchjEnd;
    }

    public String getWxzcBegin() {
        return wxzcBegin;
    }

    public void setWxzcBegin(String wxzcBegin) {
        this.wxzcBegin = wxzcBegin;
    }

    public String getWxzcEnd() {
        return wxzcEnd;
    }

    public void setWxzcEnd(String wxzcEnd) {
        this.wxzcEnd = wxzcEnd;
    }

    public String getStdlzcBegin() {
        return stdlzcBegin;
    }

    public void setStdlzcBegin(String stdlzcBegin) {
        this.stdlzcBegin = stdlzcBegin;
    }

    public String getStdlzcEnd() {
        return stdlzcEnd;
    }

    public void setStdlzcEnd(String stdlzcEnd) {
        this.stdlzcEnd = stdlzcEnd;
    }

    public String getZczjBegin() {
        return zczjBegin;
    }

    public void setZczjBegin(String zczjBegin) {
        this.zczjBegin = zczjBegin;
    }

    public String getZczjEnd() {
        return zczjEnd;
    }

    public void setZczjEnd(String zczjEnd) {
        this.zczjEnd = zczjEnd;
    }

    public String getDqjdBegin() {
        return dqjdBegin;
    }

    public void setDqjdBegin(String dqjdBegin) {
        this.dqjdBegin = dqjdBegin;
    }

    public String getDqjdEnd() {
        return dqjdEnd;
    }

    public void setDqjdEnd(String dqjdEnd) {
        this.dqjdEnd = dqjdEnd;
    }

    public String getYfkxBegin() {
        return yfkxBegin;
    }

    public void setYfkxBegin(String yfkxBegin) {
        this.yfkxBegin = yfkxBegin;
    }

    public String getYfkxEnd() {
        return yfkxEnd;
    }

    public void setYfkxEnd(String yfkxEnd) {
        this.yfkxEnd = yfkxEnd;
    }
    
    public String getYfgzBegin() {
		return yfgzBegin;
	}

	public void setYfgzBegin(String yfgzBegin) {
		this.yfgzBegin = yfgzBegin;
	}

	public String getYfgzEnd() {
		return yfgzEnd;
	}

	public void setYfgzEnd(String yfgzEnd) {
		this.yfgzEnd = yfgzEnd;
	}

	public String getYjsjBegin() {
        return yjsjBegin;
    }

    public void setYjsjBegin(String yjsjBegin) {
        this.yjsjBegin = yjsjBegin;
    }

    public String getYjsjEnd() {
        return yjsjEnd;
    }

    public void setYjsjEnd(String yjsjEnd) {
        this.yjsjEnd = yjsjEnd;
    }

    public String getYszkBegin() {
        return yszkBegin;
    }

    public void setYszkBegin(String yszkBegin) {
        this.yszkBegin = yszkBegin;
    }

    public String getYszkEnd() {
        return yszkEnd;
    }

    public void setYszkEnd(String yszkEnd) {
        this.yszkEnd = yszkEnd;
    }

    public String getYtfyBegin() {
        return ytfyBegin;
    }

    public void setYtfyBegin(String ytfyBegin) {
        this.ytfyBegin = ytfyBegin;
    }

    public String getYtfyEnd() {
        return ytfyEnd;
    }

    public void setYtfyEnd(String ytfyEnd) {
        this.ytfyEnd = ytfyEnd;
    }

    public String getYjfzBegin() {
        return yjfzBegin;
    }

    public void setYjfzBegin(String yjfzBegin) {
        this.yjfzBegin = yjfzBegin;
    }

    public String getYjfzEnd() {
        return yjfzEnd;
    }

    public void setYjfzEnd(String yjfzEnd) {
        this.yjfzEnd = yjfzEnd;
    }

    public String getYnndqdcqfzBegin() {
        return ynndqdcqfzBegin;
    }

    public void setYnndqdcqfzBegin(String ynndqdcqfzBegin) {
        this.ynndqdcqfzBegin = ynndqdcqfzBegin;
    }

    public String getYnndqdcqfzEnd() {
        return ynndqdcqfzEnd;
    }

    public void setYnndqdcqfzEnd(String ynndqdcqfzEnd) {
        this.ynndqdcqfzEnd = ynndqdcqfzEnd;
    }

    public String getQtldfzBegin() {
        return qtldfzBegin;
    }

    public void setQtldfzBegin(String qtldfzBegin) {
        this.qtldfzBegin = qtldfzBegin;
    }

    public String getQtldfzEnd() {
        return qtldfzEnd;
    }

    public void setQtldfzEnd(String qtldfzEnd) {
        this.qtldfzEnd = qtldfzEnd;
    }

    public String getLdfzhjBegin() {
        return ldfzhjBegin;
    }

    public void setLdfzhjBegin(String ldfzhjBegin) {
        this.ldfzhjBegin = ldfzhjBegin;
    }

    public String getLdfzhjEnd() {
        return ldfzhjEnd;
    }

    public void setLdfzhjEnd(String ldfzhjEnd) {
        this.ldfzhjEnd = ldfzhjEnd;
    }

    public String getCqjdBegin() {
        return cqjdBegin;
    }

    public void setCqjdBegin(String cqjdBegin) {
        this.cqjdBegin = cqjdBegin;
    }

    public String getCqjdEnd() {
        return cqjdEnd;
    }

    public void setCqjdEnd(String cqjdEnd) {
        this.cqjdEnd = cqjdEnd;
    }

    public String getCqyfkBegin() {
        return cqyfkBegin;
    }

    public void setCqyfkBegin(String cqyfkBegin) {
        this.cqyfkBegin = cqyfkBegin;
    }

    public String getCqyfkEnd() {
        return cqyfkEnd;
    }

    public void setCqyfkEnd(String cqyfkEnd) {
        this.cqyfkEnd = cqyfkEnd;
    }

    public String getQtcqfzBegin() {
        return qtcqfzBegin;
    }

    public void setQtcqfzBegin(String qtcqfzBegin) {
        this.qtcqfzBegin = qtcqfzBegin;
    }

    public String getQtcqfzEnd() {
        return qtcqfzEnd;
    }

    public void setQtcqfzEnd(String qtcqfzEnd) {
        this.qtcqfzEnd = qtcqfzEnd;
    }

    public String getCqfzhjBegin() {
        return cqfzhjBegin;
    }

    public void setCqfzhjBegin(String cqfzhjBegin) {
        this.cqfzhjBegin = cqfzhjBegin;
    }

    public String getCqfzhjEnd() {
        return cqfzhjEnd;
    }

    public void setCqfzhjEnd(String cqfzhjEnd) {
        this.cqfzhjEnd = cqfzhjEnd;
    }

    public String getStdlfzBegin() {
        return stdlfzBegin;
    }

    public void setStdlfzBegin(String stdlfzBegin) {
        this.stdlfzBegin = stdlfzBegin;
    }

    public String getStdlfzEnd() {
        return stdlfzEnd;
    }

    public void setStdlfzEnd(String stdlfzEnd) {
        this.stdlfzEnd = stdlfzEnd;
    }

    public String getFzhjBegin() {
        return fzhjBegin;
    }

    public void setFzhjBegin(String fzhjBegin) {
        this.fzhjBegin = fzhjBegin;
    }

    public String getFzhjEnd() {
        return fzhjEnd;
    }

    public void setFzhjEnd(String fzhjEnd) {
        this.fzhjEnd = fzhjEnd;
    }

    public String getFxdxjzcBegin() {
        return fxdxjzcBegin;
    }

    public void setFxdxjzcBegin(String fxdxjzcBegin) {
        this.fxdxjzcBegin = fxdxjzcBegin;
    }

    public String getFxdxjzcEnd() {
        return fxdxjzcEnd;
    }

    public void setFxdxjzcEnd(String fxdxjzcEnd) {
        this.fxdxjzcEnd = fxdxjzcEnd;
    }

    public String getXdxjzcBegin() {
        return xdxjzcBegin;
    }

    public void setXdxjzcBegin(String xdxjzcBegin) {
        this.xdxjzcBegin = xdxjzcBegin;
    }

    public String getXdxjzcEnd() {
        return xdxjzcEnd;
    }

    public void setXdxjzcEnd(String xdxjzcEnd) {
        this.xdxjzcEnd = xdxjzcEnd;
    }

    public String getJzchjBegin() {
        return jzchjBegin;
    }

    public void setJzchjBegin(String jzchjBegin) {
        this.jzchjBegin = jzchjBegin;
    }

    public String getJzchjEnd() {
        return jzchjEnd;
    }

    public void setJzchjEnd(String jzchjEnd) {
        this.jzchjEnd = jzchjEnd;
    }

    public String getFzhjzchjBegin() {
        return fzhjzchjBegin;
    }

    public void setFzhjzchjBegin(String fzhjzchjBegin) {
        this.fzhjzchjBegin = fzhjzchjBegin;
    }

    public String getFzhjzchjEnd() {
        return fzhjzchjEnd;
    }

    public void setFzhjzchjEnd(String fzhjzchjEnd) {
        this.fzhjzchjEnd = fzhjzchjEnd;
    }

	public String[] getReportFinance() {
		return reportFinance;
	}

	public void setReportFinance(String[] reportFinance) {
		this.reportFinance = reportFinance;
	}
}