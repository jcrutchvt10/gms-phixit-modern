package com.gmsphixit.app.core.phixit.service;

import com.gmsphixit.app.core.phixit.model.ParcelableFlagModel;

interface IPhixitFlagsService {
    List<ParcelableFlagModel> getAllFlags(String packageName);
    List<ParcelableFlagModel> getBoolFlags(String packageName);
    void updateFlag(String packageName, String flagName, boolean newValue);
    void addBoolFlag(String packageName, String flagName, boolean value);
    List<String> getAllConfigPackages();
    int getDbVersion();
}
