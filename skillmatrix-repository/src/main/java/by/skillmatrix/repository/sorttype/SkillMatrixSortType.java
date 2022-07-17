package by.skillmatrix.repository.sorttype;

public enum SkillMatrixSortType {
    CREATION_DATE_ASC,
    CREATION_DATE_DESC;

    public static SkillMatrixSortType getTypeByString(String type) {
        if (type == null) {
            return CREATION_DATE_ASC;
        }
        switch (type) {
            case "date.a":
                return CREATION_DATE_ASC;
            case "date.d":
                return CREATION_DATE_DESC;
        }
        return CREATION_DATE_ASC;
    }
}
