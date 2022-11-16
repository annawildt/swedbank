package APIObjects;

public class BankIDRequest {
    private boolean bankIdOnSameDevice;
    private boolean generateEasyLoginId;
    private boolean useEasyLogin;
    private long userId;

    public BankIDRequest(boolean bankIdOnSameDevice, boolean generateEasyLoginId, boolean useEasyLogin, long userId) {
        this.userId = userId;
        this.bankIdOnSameDevice = bankIdOnSameDevice;
        this.generateEasyLoginId = generateEasyLoginId;
        this.useEasyLogin = useEasyLogin;
    }

    public boolean isBankIdOnSameDevice() {
        return bankIdOnSameDevice;
    }

    public boolean isGenerateEasyLoginId() {
        return generateEasyLoginId;
    }

    public boolean isUseEasyLogin() {
        return useEasyLogin;
    }

    public long getUserId() {
        return userId;
    }
}