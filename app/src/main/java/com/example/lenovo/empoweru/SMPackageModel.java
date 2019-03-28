package com.example.lenovo.empoweru;

import java.util.List;

class SMPackageModel {

    private String packageName;
    private List<String> priceList;
    private String presenties;

    public SMPackageModel(radrec radrec) {
    }

    public SMPackageModel(Tab2Fragment tab2Fragment) {
    }

    public SMPackageModel(DistrictTab2Fragment districtTab2Fragment) {
    }

    public SMPackageModel(CRPTab2Fragment crpTab2Fragment) {
    }

    public SMPackageModel(BlockTab2Fragment blockTab2Fragment) {
    }

    public SMPackageModel(SMBlockSchoolMonitor smBlockSchoolMonitor) {
    }

    public String getPresenties() {
        return presenties;
    }

    public void setPresenties(String presenties) {
        this.presenties = presenties;
    }

    public SMPackageModel(String hd_movie_tv_channels, List<String> priceList) {
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
