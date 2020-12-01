package com.analysis.algorithm;

public enum Algorithm {

    /**
     * 时域分析
     */
    TimeDomain("timeDomain", "TimeDomainAnalysis"),

    /**
     * 频域分析
     */
    FrequencyDomain("frequencyDomain", "FrequencyDomainAnalysis"),

    /**
     * 小波分析
     */
    Wavelet("wavelet","WaveletAnalysis"),

    /**
     * 灰色关联度故障诊断
     */
    GreyRelationalGrade("greyRelational","GreyRelationalGrade"),

    /**
     * 特征分解故障预测
     */
    FeatureDecomposition("featureDecomposition","FeatureDecomposition");

    private String algorithmName;

    private String algorithmClass;

    Algorithm(String algorithmName, String algorithmClass) {
        this.algorithmName = algorithmName;
        this.algorithmClass = algorithmClass;
    }

    public String getAlgorithmName() {
        return this.algorithmName;
    }

    public String getAlgorithmClass() {
        return this.algorithmClass;
    }

}
