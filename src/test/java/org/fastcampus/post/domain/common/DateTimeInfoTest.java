package org.fastcampus.post.domain.common;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateTimeInfoTest {

    @Test
    void givenCreated_whenUpdated_thenTimeAndStateArsUpdated(){
        //given
        DatetimeInfo datetimeInfo = new DatetimeInfo();
        LocalDateTime localDateTime = datetimeInfo.getDateTime();

        //when
        datetimeInfo.updateEditDateTime();

        //then
        assertTrue(datetimeInfo.isEdited());
        assertNotEquals(localDateTime, datetimeInfo.getDateTime());
    }
}
