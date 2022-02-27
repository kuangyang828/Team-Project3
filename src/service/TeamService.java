package service;

import domain.Architect;
import domain.Designer;
import domain.Employee;
import domain.Programmer;

/**
 * 关于开发团队成员的管理：添加、删除等
 */
public class TeamService {
    private static int counter = 1;//用来为开发团队新增成员自动生成团队中的唯一ID，即memberId。（提示：应使用增1的方式）
    private final int MAX_MEMBER = 5;//限制开发团队最大成员数
    private Programmer[] team = new Programmer[MAX_MEMBER];//保存当前团队中的各成员对象
    private int total = 0;//记录团队成员的实际人数

    /**
     * 返回当前团队的所有成员
     *
     * @return 包含所有成员对象的数组，数组大小与成员人数一致
     */
    public Programmer[] getTeam() {
        Programmer[] team = new Programmer[total];
        for (int i = 0; i < team.length; i++) {
            team[i] = this.team[i];
        }
        return team;
    }

    /**
     * 向团队中添加指定的成员
     *
     * @param e 待添加成员的对象
     */
    public void addMember(Employee e) throws TeamException {
        //成员已满，无法添加
        if (total >= MAX_MEMBER) {
            throw new TeamException("成员已满，无法添加");
        }
        //该成员不是开发人员，无法添加
        if (!(e instanceof Programmer)) {
            throw new TeamException("该成员不是开发人员，无法添加");
        }
        //该员工已在本开发团队中
        if (isExist(e)) {
            throw new TeamException("该员工已在本开发团队中");
        }
        //该员工已是某团队成员
        if ("BUSY".equals(((Programmer) e).getStatus().getNAME())) { //强转一定不会出现ClassCastException
            throw new TeamException("该员工已是某团队成员");
        }
        //该员正在休假，无法添加
        if ("VOCATION".equals(((Programmer) e).getStatus().getNAME())) { //强转一定不会出现ClassCastException
            throw new TeamException("该员正在休假，无法添加");
        }
        //团队中至多只能有一名架构师
        //团队中至多只能有两名设计师
        //团队中至多只能有三名程序员

        //获取team已有构师，设计师，程序员的人数
        int numOfArch = 0, numOfDes = 0, numOfPro = 0;
        for (int i = 0; i < total; i++) {
            if (e instanceof Architect) {
                numOfArch++;
            } else if (e instanceof Designer) {
                numOfDes++;
            } else if (e instanceof Programmer) {
                numOfPro++;
            }
        }

        if (e instanceof Architect) {
            if (numOfArch >= 1) {
                throw new TeamException("团队中至多只能有一名架构师");
            }
        } else if (e instanceof Designer) {
            if (numOfDes >= 2) {
                throw new TeamException("团队中至多只能有两名设计师");
            }
        } else if (e instanceof Programmer) {
            if (numOfPro >= 3) {
                throw new TeamException("团队中至多只能有三名程序员");
            }
        }

        /*
        将e添加到现有的team中
         */
        Programmer p = (Programmer) e;
        //team[total] = p;
        //total++;
        //或
        team[total++] = p;
        //将该成员的Status改为BUSY
        p.setStatus(Status.BUSY);
        //团队成员ID++
        p.setMemberId(counter++);

    }

    private boolean isExist(Employee e) {
        for (int i = 0; i < total; i++) {
            if (e.getId() == team[i].getId()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 从团队中删除成员
     *
     * @param memberId 待删除成员的memberId
     */
    public void removeMember(int memberId) throws TeamException {
        int i = 0;
        for (; i < total; i++) {
            if (team[i].getId() == memberId) {
                team[i].setStatus(Status.FREE);
                break;
            }
        }

        //未找到指定memberId的员工
        if (i == total) {
            throw new TeamException("找不到指定memberId的员工，删除失败");
        }

        //后一个元素覆盖前一个元素，实现删除操作
        for (int j = i + 1; j < total; j++) {
            team[j - 1] = team[j];
        }

        //写法一
        team[total] = null;
        total--;
        //写法二
//        team[--total] = null;

    }
}
