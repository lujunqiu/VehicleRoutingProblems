import java.util.*;

/**
 * Created by qiu on 17-5-4.
 */
public class demo {
    public static void main(String[] args) {
        int[] ints = {1,1,1,2,3,5,1};
        System.out.println(findPeakElement(ints));
    }

    static public int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        for (int i = 0; i < nums.length; i++) {
            if (ifPeadElement(i, nums) == true) {
                return i;
            }
        }
        return 0;
    }

    static private boolean ifPeadElement(int index, int[] nums) {

        if (index == 0) {//至少有２个元素
            return nums[index + 1] < nums[index];
        }
        if (index == nums.length - 1) {
            return nums[index] > nums[index - 1];
        }

        return nums[index] > nums[index - 1] && nums[index] > nums[index + 1];
    }
}
