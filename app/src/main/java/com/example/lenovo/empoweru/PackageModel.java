package com.example.lenovo.empoweru;

import java.util.List;

class PackageModel {

    private String packageName;
    private List<String> priceList;
    private String presenties;

    public PackageModel(radrec radrec) {
    }

    public PackageModel(Tab2Fragment tab2Fragment) {
    }

    public PackageModel(DistrictTab2Fragment districtTab2Fragment) {
    }

    public PackageModel(CRPTab2Fragment crpTab2Fragment) {
    }

    public PackageModel(BlockTab2Fragment blockTab2Fragment) {
    }

    public PackageModel(SMBlockSchoolMonitor smBlockSchoolMonitor) {
    }

    public PackageModel(SMDistrictSchoolMonitor smDistrictSchoolMonitor) {

    }

    public PackageModel(SMCRPSchoolMonitor smcrpSchoolMonitor) {

    }

    public String getPresenties() {
        return presenties;
    }

    public void setPresenties(String presenties) {
        this.presenties = presenties;
    }

    public PackageModel(String hd_movie_tv_channels, List<String> priceList) {
      this.packageName=hd_movie_tv_channels;
      this.priceList= priceList;
    }

    public String getPackageName() {
        return packageName;
    }

    public List<String> getPriceList() {
        return priceList;
    }
}
