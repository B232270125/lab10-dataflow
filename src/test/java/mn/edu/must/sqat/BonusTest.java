package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BonusTest {

    // ---------- ALL-DEFS (2 tests) ----------

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

    // ---------- ALL-DU-PAIRS (additional tests) ----------

    @Test
    @DisplayName("DU1: null, 3 -> exception; attendance 31-32(T)")
    void du1_nullAttendance() {
        assertThrows(IllegalArgumentException.class, () -> DataFlowTargets.bonus(null, 3));
    }

    @Test
    @DisplayName("DU2: {1}, -1 -> exception; threshold 31-32(T)")
    void du2_negativeThreshold() {
        assertThrows(IllegalArgumentException.class, () -> DataFlowTargets.bonus(new int[] {1}, -1));
    }

    @Test
    @DisplayName("DU3: {}, 0 -> 5; present 35-50(T), threshold 31-50(T), score 51-56, best 37-53(F)")
    void du3_emptyThresholdZero() {
        assertEquals(5, DataFlowTargets.bonus(new int[] {}, 0));
    }

    @Test
    @DisplayName("DU4: {1}, 2 -> 0; present 40-50(F), streak 41-42(T), best 37-42(T), best 43-53(F)")
    void du4_singleBelowThreshold() {
        assertEquals(0, DataFlowTargets.bonus(new int[] {1}, 2));
    }

    @Test
    @DisplayName("DU5: {1}, 1 -> 5; present 40-50(T), threshold 31-50(T)")
    void du5_singleMeetsThreshold() {
        assertEquals(5, DataFlowTargets.bonus(new int[] {1}, 1));
    }

    @Test
    @DisplayName("DU6: {1,0,1}, 5 -> 0; a 38-39(F), streak 46-41, streak 41-42(F), best 43-42(F)")
    void du6_resetThenOne() {
        assertEquals(0, DataFlowTargets.bonus(new int[] {1, 0, 1}, 5));
    }

    @Test
    @DisplayName("DU7: {1,1,1,1,1}, 6 -> 3; score 49-54, best 43-53(T), present 40-50(F)")
    void du7_fiveStreakBelowThreshold() {
        assertEquals(3, DataFlowTargets.bonus(new int[] {1, 1, 1, 1, 1}, 6));
    }
}
