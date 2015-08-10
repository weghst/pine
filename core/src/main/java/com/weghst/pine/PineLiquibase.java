package com.weghst.pine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import liquibase.integration.spring.SpringLiquibase;

public class PineLiquibase extends SpringLiquibase {

    class MySpringResourceOpener extends SpringResourceOpener {

        MySpringResourceOpener(String parentFile) {
            super(parentFile);
        }

        @Override
        public Set<String> list(String relativeTo, String path, boolean includeFiles,
                                boolean includeDirectories, boolean recursive) throws IOException {

            Set<String> paths = super.list(relativeTo, path, includeFiles, includeDirectories,
                    recursive);
            if (paths == null) {
                return null;
            }

            List<String> sortedPaths = new ArrayList<>(paths);

            // 按文件名称中的版本号进行排序(升序)
            Collections.sort(sortedPaths, new Comparator<String>() {
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
            });


            return (new HashSet<>(sortedPaths));
        }
    }

    @Override
    protected SpringResourceOpener createResourceOpener() {
        return new MySpringResourceOpener(getChangeLog());
    }
}
