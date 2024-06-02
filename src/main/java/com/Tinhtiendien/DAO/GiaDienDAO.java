package com.Tinhtiendien.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Tinhtiendien.Entity.MapperGiaDien;
import com.Tinhtiendien.Models.GiaDien;

@Repository
public class GiaDienDAO {

    @Autowired
    public JdbcTemplate jdbcTemplate;
    
    public List<GiaDien> HienThiDanhSach() {
        String query = "select * from gia_dien";
        List<GiaDien> danhSach_GiaDien = new ArrayList<GiaDien>();
        try {
            danhSach_GiaDien = jdbcTemplate.query(query, new MapperGiaDien());
        } catch (DataAccessException e) {
            System.out.println("Truy vấn thất bại");
            e.printStackTrace();
        }
        return danhSach_GiaDien;
    }

    public void Xoa(int bacDien) {
        String sql = "delete from gia_dien where bac = ?";
        try {
            jdbcTemplate.update(sql, bacDien);    
        } catch (Exception e) {
            System.out.println("Xóa thất bại");
            e.printStackTrace();
        }    
    }

    public void Them(int add_bacDien, int add_giaDien) {
        String sql = "insert into gia_dien (bac, giatien) values (?, ?)";
        try {
            jdbcTemplate.update(sql, new Object[] { add_bacDien, add_giaDien });
        } catch (Exception e) {
            System.out.println("Thêm thất bại");
            e.printStackTrace();
        }
    }

    public void CapNhat(int bacDien, int edit_giaDien) {
        String sql = "update gia_dien set giatien = ? where bac = ?";
        try {
            jdbcTemplate.update(sql, new Object[] { edit_giaDien, bacDien });
        } catch (Exception e) {
            System.out.println("Cập nhật thất bại");
            e.printStackTrace();
        }
    }
    public boolean checkExist(int bacDien) {
        try {
            String sql = "select * from gia_dien where bac = ?";
            GiaDien isExist = jdbcTemplate.queryForObject(sql, new Object[]{bacDien}, new MapperGiaDien());
            return true;
        } catch (Exception e) {
            System.out.println("Truy vấn thất bại");
            e.printStackTrace();
            return false;
        }
    }

}
