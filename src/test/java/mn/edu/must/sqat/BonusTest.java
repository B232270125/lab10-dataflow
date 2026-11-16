package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BonusTest {

    @Test
    @DisplayName("AD1: {1,1,0,1,1,1,1,1}, thr=3 -> 8; defs 36,40,41,43,46,38,51,54 reach uses")
    void ad1_longStreakAfterReset() {
        assertEquals(8, DataFlowTargets.bonus(new int[] {1, 1, 0, 1, 1, 1, 1, 1}, 3));
    }

    @Test
    @DisplayName("AD2: {}, thr=1 -> 0; def 49 -> use 56, def 35 -> 50, def 37 -> 53")
    void ad2_emptyBelowThreshold() {
        assertEquals(0, DataFlowTargets.bonus(new int[] {}, 1));
    }
}
