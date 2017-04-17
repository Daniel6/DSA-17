public class FirstFailingVersion {

    public static long firstBadVersion(long n, IsFailingVersion isBadVersion) {
        return helper(n, n/2, isBadVersion);
    }

    public static long helper(long n, long step, IsFailingVersion isBadVersion) {
        if (step < 1) step = 1;
        if (isBadVersion.isFailingVersion(n)) {
            if (!isBadVersion.isFailingVersion(n - 1)) {
                // If this version fails and the previous one didn't, return this version
                return n;
            } else {
                return helper(n - step, step / 2, isBadVersion);
            }
        } else {
            return helper(n + step, step / 2, isBadVersion);
        }
    }
}
