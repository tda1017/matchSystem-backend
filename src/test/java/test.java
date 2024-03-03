import java.util.*;

/**
 * @author: TDA
 * @date: 2023/12/31 13:00
 * @description:
 */
public class test {
    /**
     * 给你一个字符串 date ，按 YYYY-MM-DD 格式表示一个 现行公元纪年法 日期。返回该日期是当年的第几天。
     */
    public static int dayOfYear(String date) {
        //月份和日期都是两位
        String[] buff = date.split("-");
        StringBuilder yearSb = new StringBuilder();
        StringBuilder monthSb = new StringBuilder();
        StringBuilder daySb = new StringBuilder();
        yearSb.append(buff[0]);
        monthSb.append(buff[1]);
        daySb.append(buff[2]);
        int year = Integer.parseInt(yearSb.toString());
        int month = Integer.parseInt(monthSb.toString());
        int day = Integer.parseInt(daySb.toString());

        int res = 0;
        int[] runMonthDays = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] noRunMonthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        //闰年
        if (month == 1) return day;
        if (year % 4 == 0) {
            if (year % 100 == 0 && year % 400 != 0) {
                //去掉能被100不能被400
                for (int i = 0; i < month; i++) {
                    if (month - 1 == i) res += day;
                    else {
                        res += noRunMonthDays[i];
                    }
                }
            } else {
                for (int i = 0; i < month; i++) {
                    if (month - 1 == i) res += day;
                    else {
                        res += runMonthDays[i];
                    }
                }
            }

        }
        if (year % 4 != 0) {
            for (int i = 0; i < month; i++) {
                if (month - 1 == i) res += day;
                else {
                    res += noRunMonthDays[i];
                }
            }
        }
        return res;
    }

    /**
     * 给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。
     * 输入为三个整数：day、month 和 year，分别表示日、月、年。
     * 您返回的结果必须是这几个值中的一个 {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}。
     */

    public static String dayOfTheWeek(int day, int month, int year) {
        String[] resString = {"Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"};
        int[] runMonthDays = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] noRunMonthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int days = 0;//总天数
        if (year % 4 == 0) {
            if (year % 100 == 0 && year % 400 != 0) {
                //去掉能被100不能被400
                for (int i = 0; i < month; i++) {
                    if (month - 1 == i) days += day;
                    else {
                        days += noRunMonthDays[i];
                    }
                }
            }
            //真闰年
            else {
                for (int i = 0; i < month; i++) {
                    if (month - 1 == i) days += day;
                    else {
                        days += runMonthDays[i];
                    }
                }
            }
        } else {
            for (int i = 0; i < month; i++) {
                if (month - 1 == i) days += day;
                else {
                    days += noRunMonthDays[i];
                }
            }
        }
        int yearDays = 0;
        for (int i = 1971; i < year; i++) {
            if (i % 4 != 0 || (i % 100 == 0 && i % 400 != 0)) {
                yearDays += 365;
            } else {
                yearDays += 366;
            }
        }

        int index = (days + yearDays) % 7;
        if (index == 0) return resString[6];
        return resString[index - 1];
    }

    /**
     * 给你一个整数数组 prices ，它表示一个商店里若干巧克力的价格。同时给你一个整数 money ，表示你一开始拥有的钱数。
     * 你必须购买 恰好 两块巧克力，而且剩余的钱数必须是 非负数 。同时你想最小化购买两块巧克力的总花费。
     * 请你返回在购买两块巧克力后，最多能剩下多少钱。如果购买任意两块巧克力都超过了你拥有的钱，请你返回 money 。注意剩余钱数必须是非负数。
     *
     * @param
     */
    public static int buyChoco(int[] prices, int money) {
        int res = -1;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[i] + prices[j] > money) {
                    continue;
                } else if ((money - (prices[i] + prices[j])) > res && (money - (prices[i] + prices[j])) >= 0) {
                    res = money - (prices[i] + prices[j]);
                }
            }
        }
        if (res == -1) return money;
        return res;
    }

    /**
     * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
     * <p>
     * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
     * <p>
     * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
     * eat
     * aet
     * []
     * aet - [eat]
     * [[eat]]
     * tea
     * aet
     * aet - [eat]
     * tea - [eat,tea]
     * [[],[],[]]
     *
     * @param args
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> hashmap = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            //将排序后的字符数组转换成string
            String key = new String(array);
            //如果哈希表里没有key，则创建新数组把key塞进去，如果有，就
            List<String> list = hashmap.getOrDefault(key, new ArrayList<>());
            list.add(str);
            hashmap.put(str, list);
        }
        return new ArrayList<List<String>>(hashmap.values());

    }


    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * <p>
     * 你可以按任意顺序返回答案。
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     */

    public static int[] twoSum(int[] nums, int target) {
        //1.暴力
//        for(int i = 0;i < nums.length-1;i++){
//            for(int j = i + 1;j < nums.length;j++){
//                if(nums[i] + nums[j] == target) {
//                    return new int[]{i,j};
//                }
//            }
//        }

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1};
    }

    /**
     * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     * <p>
     * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
     * 输入：nums = [100,4,200,1,3,2]
     * 输出：4
     * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
     *
     * @param args nums
     */

    public static int longestConsecutive(int[] nums) {
        //把数组转换成set
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            //没有前驱-1才从这里开始判断
            if (!set.contains(nums[i] - 1)) {
                int currentNum = nums[i];
                int currentMax = 1;
                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    currentMax++;
                    if (currentMax == nums.length) break;
                }
                if (currentMax > max) max = currentMax;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[]{}));
        
    }
}
