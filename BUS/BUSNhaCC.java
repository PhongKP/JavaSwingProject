package BUS;

public class BUSNhaCC {
    private static BUSNhaCC instance;
    public static BUSNhaCC getInstance(){
        if (instance==null){
            instance= new BUSNhaCC();
        }
        return instance;
    }
    public java.util.List<Modal.DTONhaCC> getAllNhaCC(){
        return DAO.DAONhaCC.getInstance().getAllNhaCC();
    }
    public boolean checkIfExistsPhone(String manhacc,String phone){
        return DAO.DAONhaCC.getInstance().checkIfExistsPhone(manhacc,phone);
    }
    public boolean edit(Modal.DTONhaCC dto){
        return DAO.DAONhaCC.getInstance().edit(dto);
    }
    public Modal.DTONhaCC getNhaCCByID(String manhacc){
        return DAO.DAONhaCC.getInstance().getNhaCCByID(manhacc);
    }
    public boolean delete(String manhacc){
        return DAO.DAONhaCC.getInstance().delete(manhacc);
    }
    public boolean isMaNhaCCExists(String manhacc){
        return DAO.DAONhaCC.getInstance().isMaNhaCCExists(manhacc);
    }
    public boolean isExistsPhone(String sdt){
        return DAO.DAONhaCC.getInstance().isExistsPhone(sdt);
    }
    public boolean add(Modal.DTONhaCC dto){
        return DAO.DAONhaCC.getInstance().add(dto);
    }
    public java.util.List<Modal.DTONhaCC> getNhaCCByIDOrName(String like){
        return DAO.DAONhaCC.getInstance().getNhaCCByIDOrName(like);
    }
}
