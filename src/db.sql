--NETCTOSS
select * from cost_chenqf;
select * from account_chenqf;
select * from service_chenqf;
select * from service_chenqf_update_bak;
select * from module_info_chenqf;
select * from role_info_chenqf;
select * from role_module_chenqf;
select * from admin_info_chenqf;
select * from admin_role_chenqf;
--DEMO
select * from dept_chenqf;
select * from emp_chenqf;


select * from
			(select a.*,rownum  from
				(select * from admin_info_chenqf
					where admin_id in(
						select ai.admin_id from
					 	admin_info_chenqf ai
						left join admin_role_chenqf  ar on ai.admin_id=ar.admin_id
						left join role_info_chenqf   ri on ar.role_id =ri.role_id
						left join role_module_chenqf rm on ri.role_id =rm.role_id
						left join module_info_chenqf mi on rm.module_id=mi.module_id
						
					)
				order by admin_id ) a
			) 
		where rownum <11