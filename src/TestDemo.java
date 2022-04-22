import DAO.DaoModel;

public class TestDemo {
    public static void main(String[] args) {
        DaoModel dao = new DaoModel();
//        int i = dao.retrieveAccountID("Husband");
//        int j = dao.retrieveTypeID("Food","Expense");
//        System.out.println(i);
//        System.out.println(j);
        System.out.println(dao.retrieveLatestRid());


    }
}
