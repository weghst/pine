package com.weghst.pine.liquibase;

import com.weghst.pine.Version;

import java.util.Comparator;

import liquibase.changelog.DatabaseChangeLog;

public class PineDatabaseChangeLog extends DatabaseChangeLog {

    public PineDatabaseChangeLog(String physicalFilePath) {
        super(physicalFilePath);
    }

    @Override
    protected Comparator<String> getStandardChangeLogComparator() {
        return new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                Version v1 = getVersion(o1);
                Version v2 = getVersion(o2);
                return v1.compareTo(v2);
            }

            Version getVersion(String path) {
                String filename = path.substring(path.lastIndexOf('/') + 1);
                int beginIndex = filename.indexOf("-") + 1;
                int endIndex = filename.lastIndexOf(".");
                return new Version(filename.substring(beginIndex, endIndex));
            }
        };
    }

}
