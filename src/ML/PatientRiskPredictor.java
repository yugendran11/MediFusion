package ML;

public class PatientRiskPredictor {

    public String predictRisk(
            int age,
            double temperature,
            int heartRate,
            int fever,
            int cough,
            int fatigue,
            int breathingDifficulty) {

        int riskScore = 0;

        // Risk calculation
        if (age >= 60) {
            riskScore += 2;
        } else if (age >= 40) {
            riskScore += 1;
        }

        if (temperature >= 39) {
            riskScore += 3;
        } else if (temperature >= 38) {
            riskScore += 2;
        }

        if (heartRate >= 110) {
            riskScore += 2;
        } else if (heartRate >= 95) {
            riskScore += 1;
        }

        if (fever == 1) {
            riskScore += 1;
        }

        if (cough == 1) {
            riskScore += 1;
        }

        if (fatigue == 1) {
            riskScore += 1;
        }

        if (breathingDifficulty == 1) {
            riskScore += 3;
        }

        // Final classification
        if (riskScore >= 8) {
            return "HIGH RISK";
        } else if (riskScore >= 4) {
            return "MEDIUM RISK";
        } else {
            return "LOW RISK";
        }
    }
}