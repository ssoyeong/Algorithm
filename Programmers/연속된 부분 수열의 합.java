class Solution {
	public int[] solution(int[] sequence, int k) {
        
        int n = sequence.length;
        int[] ans = new int[2];
        int start = 0;
        int end = 0;
        int sum = 0;
        int len = n + 1;

        while (start < n) {

            if(sum > k) {
                sum -= sequence[start++];
            }
            else if(sum < k) {
                if(end < n) sum += sequence[end++];
                else sum -= sequence[start++];
            }
            else {
                if(len > end - start) {
                    len = end - start;
                    ans[0] = start;
                    ans[1] = end - 1;
                }
                sum -= sequence[start++];
            }
        }
        return ans;
    }
}
