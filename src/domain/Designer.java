package domain;

public class Designer extends Programmer {
    private double bonus;//奖金

    public Designer() {

    }

    public Designer(int id, String name, int age, double salary, Equipment equipment, double bonus) {
        super(id, name, age, salary, equipment);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return getDetalis() + "\t\t\t设计师\t\t\t" + getStatus() + "\t\t\t\t" + bonus + "\t\t" + getEquipment().getDescription();
    }

    public String getDetailsForTeam() {
        return getMemberId() + "/" + getId() + "\t" + getName() + "\t" + getAge() + "\t" + getSalary() + "\t设计师\t"+getBonus();
    }
}
