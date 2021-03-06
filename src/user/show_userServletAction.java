package user;

import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asua-pc on 2017/5/12.
 */
public class show_userServletAction extends HttpServlet {
    public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{

    }
    public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        List jsonList = new ArrayList();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException classnotfoundexception) {
            classnotfoundexception.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=ylx&password=ylx&useUnicode=true&characterEncoding=UTF-8");
            Statement statement = conn.createStatement();

            String sql="select * from user_file order by id";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                List list = new ArrayList();
                list.add(rs.getString("user_id"));
                list.add(rs.getString("user_name"));
                list.add(rs.getString("user_gender"));
                list.add(rs.getString("user_password"));
                list.add(rs.getString("add_time"));
                list.add(rs.getString("id"));
                jsonList.add(list);
            }
            statement.close();
            conn.close();
        } catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
        //////////Êý¾Ý¿â²éÑ¯Íê±Ï£¬µÃµ½ÁËjsonÊý×éjsonList//////////
        //ÏÂÃæ¿ªÊ¼¹¹½¨·µ»ØµÄjson
        JSONObject json=new JSONObject();
        try {
            json.put("aaData",jsonList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            json.put("result_msg","ok");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            json.put("result_code",0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        response.setContentType("text/html; charset=UTF-8");
        try {
            response.getWriter().print(json);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
