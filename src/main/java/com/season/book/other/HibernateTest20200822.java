package com.season.book.other;
/**
 * �������ҵı����갥, ��Ӧ�ö�ע��Щ����, ��Ҫɵɵ��, �ǲ���һ���� , ����һ�� 2 .
 * ���� ��ƽ����� ,��˵����, ��ɼ������� ,��˵, ������ . 
 * ��Щ����Īǿ�� , ��Ϊ���������� ,����������Ǹ���� . ��Щ�Ǹ�δ����� , 
 * ���ǻ���Щ, �������Ⱦ����� ,ֻ��ʵ�������ǲ��ܼ��� , ��ʹǿ���ȰѺ������� , �����Ĵ��۲������ܳ��ܵ� .
 * ������һ�����󷽷�һ�� , ��ʱ��������Ϊ��
 * 
 * 
 * 
 */
/**
 * �������д�������ʱ�� �Ŷ���
 * 
 * ���� , ֻ��emp������ dept
 * Ҳ����˫��	�������öԷ�ʵ��
 * 		����Ķ��һ
 * set����,���Ա�֤Ψһ��
 * 
 * ��ʱ����	���� �ò��ϲ��� ʡ����  ����֢	����ģʽ
 * load
 * ֻ����ʹ�õ�ʱ���ȥ��	���ӵ������ʱ��
 * session�ر�֮��Ͳ��ܲ������
 * �����	��get
 * 		1.��Ҫ���ʱ�� �ȳ�ʼ��һ��	
 * 		2.Hibernate��initialize(e);	��ʼ������
 * 		3.���session	������
 * 		4.��hbm.xml�� lazy="false"
 * ������get��
 * 
 * get null
 * load exception
 * 		��Ϊstruts����empty�ж�
 * 
 * emp.getDept().getDname();
 * ������session�ر�֮ǰ
 * ���ǵý����ʱ����:(get,load���ᷢ��)
 * 1.Hibernate.initialize(e.getDept())
 * 2.����� <many to more>�ϼ�һ��lazy="false"
 * 
 * Hibernate��2�����д��,  �����ù�����ѯһЩд��
 * 
 * fetch="join"(�������������)-----ץȡ����	һ�������,��Ҫץȡ	
 * ����д��Զ������ʱ����	һ���ڷ�������д2��,һ����ʱ,һ������ʱ(hql�Ժ�ؽ�,���߳�ʼ��)
 * 
 * ע��no session����
 * ---------------------------------------------------------------------------
 * ----------------�����һ�Զ�--------------------------------------------------
 * ---------------------------------------------------------------------------
 *  ��ɾ��  ����cascade. ά����תinverse.     ��  ��ʱ����lazy
 * �������hbm
 * ����û��set��Ӧ��,�����������
 * �Ե��ж������
 * 
 * �ص�,�ص�,�ص�:�����ĵ�
 * 
 * ��ʱ����,�Ƚ�ʡ����,Ҫ��ϵ�
 * 1.��ʼ��
 * 2.fetch lazy
 * 3.��ʱ�ر�
 * �õ��������л����,���÷��ڶ���
 * 
 * 
 * ����,һ������,������Ҳһ�𱣴�
 * 
 * ��set�ϼ���cascade="all"
 * 
 * ���������
 * d.setDname();
 * d.setLoc();
 * Ȼ��:
 * d.getEmp().add(new Emp())
 * d.getEmp().add(new Emp())
 * d.getEmp().add(new Emp())
 * Ȼ����save()
 *   
 * �ӱ�ά�������ϵ���,�ѶԷ������Լ�����
 * 
 * ��ô��,Ч�ʵ���	---->��ת inverse="true"
 * �µĹ��췽��   ��dept�Ž�ȥ
 * new Emp(,,,dept);
 * new Emp(,,,dept);
 * new Emp(,,,dept);
 * ����ת,�Լ�ά��
 * 
 * inverse ��Ч����ߵ�,  ά����ϵ,  ��ת���Է����
 * ����cascade��һ���Զ�����
 * 
 * �����ٵ�һ���������õ�
 * 
 * ---------------------------------------------------------------------------------------------
 * -------������---------5555,�������� , ����Ϣ , ��˯��----------------------------------------------
 * ---------------------------------------------------------------------------------------------
 * 1.����ɾ��:
 * 			1.��ʱ����
 * 			2.��֪���ݲ�ȫ
 * 
 *	Ĭ��Ϊ�յ�����, ����Ĭ��ֵ, ����������, ������Ĭ��ֵ	new��ʱ�����ֵ, ���� ������ȥ�� 
 *	��ӵ�ʱ�� , �Լ�����ֵ , ��Ȼ��ɵ�Ƶĸ���Ϊ��
 *
 *	ʵ��������,�ѱ�set��(Ҳ���Ƕ��һ��)д����дhashcode equals����
 *	������һ���Ķ���ʱ��,ֱ������ž����� 
 *
 *	����,ֱ���ó���--��ȥ--��
 *	�ܶ�ʱ�� 
 * 
 * -----------------------------------------------------------------------------------------------
 * ��ͷѧһ��һ, ��Զ��7��
 * 
 * 
 * 
 */
import my.dao.DeptDao;
import my.entity.Dept;
/**
 * HQL
 * ---------------------------------------------------------------------
 * 1.��Сд����(����java)	��sql�йز�����,	��������
 * 		�Ƚ�ȫ�İ���.����(һ�㲻��Ҫ)
 * 	��','����,��������
 * 
 * �����java����?
 * 1.�õ�session
 * String hql="from dept";
 * Query query =session.createQuery(hql);
 * query.list();
 * 
 * 3�ֲ������ݷ�ʽ
 * 
 * hql="from dept where deptno = ?";
 * Hibernate��0��ʼ
 * query.setInteger(0, );
 * Dept d = (Dept)query.uniqueResult();ȡһ��ֵ
 * ��ʱ����Ҫ�־û�����
 * 
 * hql="from dept where deptno =:id";	������	�˵�deptno��ָ������,������
 * 			���ݲ���������	�����ʱ���":" ʹ�õ�ʱ��Ͳ�����
 * Hibernate��0��ʼ
 * query.setInteger("id", );
 * Dept d = (Dept)query.uniqueResult();ȡһ��ֵ
 * ��ʱ����Ҫ�־û�����
 * 
 * �ö�����ֵ�Դ洢
 * hql="from dept where dName like :name and loc like :loc";	likeƥ�䷽��,���ڸ�ֵ��ʱ���%....
 * Hibernate��0��ʼ
 * query.setString("name","%O%" );
 * query.setString("loc","%S%" );
 * Dept d = (Dept)query.uniqueResult();ȡһ��ֵ
 * ��ʱ����Ҫ�־û�����
 * 
 * hql="from dept where dName like :name and loc like :loc";	
 * Map map = new hashMap();		Ҳ������ʵ��洢(gettersetter,��װ)	map�򵥵�,ʵ�������ܺõ�		Ҳ������ʵ����
 * 								��Щʱ����Ҫ����һ��������(5~10,�з�Χ�Ǿ�ԭ���ľ���������)
 * 								��ʵ���Լ̳�����,ֻ��Ҫд�����
 * map.set("name","%O%" );
 * map.set("loc","%S%" );
 * query.setProperties(map);	����ʵ����
 * Dept d = (Dept)query.uniqueResult();ȡһ��ֵ
 * ��ʱ����Ҫ�־û�����
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
public class Test {

	public static void main(String[] args) {
		Dept d=new Dept();
		d.setDeptno(7);
		d.setDname("xiaoshou");
		d.setLoc("QD");
		
		DeptDao dd=new DeptDao();
		dd.saveDept(d);
	}

}
