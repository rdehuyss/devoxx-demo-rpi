package org.jobrunr.devoxx.common.conditional;

import com.pi4j.boardinfo.definition.BoardModel;
import com.pi4j.boardinfo.util.BoardInfoHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class IsRunningOnRPi implements Condition {

    private final Logger LOGGER = LoggerFactory.getLogger(IsRunningOnRPi.class);

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        var boardModel = BoardInfoHelper.current().getBoardModel();
        var runningOnPi = boardModel != BoardModel.UNKNOWN;
        LOGGER.info("Detected board: {}", boardModel);
        LOGGER.info("\tIs Raspberry Pi: {}", runningOnPi);
        return runningOnPi;
    }
}
