public enum PowerUpType {
    POWER_UP_1,
    POWER_UP_2,
    POWER_UP_3;

    public static PowerUpType getRandomPowerUpType() {
        PowerUpType[] types = PowerUpType.values();
        int randomIndex = (int) (Math.random() * types.length);
        return types[randomIndex];
    }
}
