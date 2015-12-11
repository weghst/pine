package com.weghst.pine.liquibase;

import liquibase.changelog.ChangeLogParameters;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.exception.ChangeLogParseException;
import liquibase.parser.core.ParsedNode;
import liquibase.parser.core.xml.XMLChangeLogSAXParser;
import liquibase.resource.ResourceAccessor;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class PineChangeLogParser extends XMLChangeLogSAXParser {

    @Override
    public DatabaseChangeLog parse(String physicalChangeLogLocation,
                                   ChangeLogParameters changeLogParameters,
                                   ResourceAccessor resourceAccessor) throws ChangeLogParseException {

        ParsedNode parsedNode = parseToNode(physicalChangeLogLocation, changeLogParameters, resourceAccessor);
        if (parsedNode == null) {
            return null;
        }

        DatabaseChangeLog changeLog = new PineDatabaseChangeLog(physicalChangeLogLocation);
        changeLog.setChangeLogParameters(changeLogParameters);
        try {
            changeLog.load(parsedNode, resourceAccessor);
        } catch (Exception e) {
            throw new ChangeLogParseException(e);
        }

        return changeLog;
    }

    @Override
    public int getPriority() {
        return 9;
    }
}
