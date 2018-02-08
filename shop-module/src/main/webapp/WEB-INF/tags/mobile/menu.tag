<%@ tag language="java" pageEncoding="utf-8" %>
<%@ attribute name="parentmenu" required="true" type="java.lang.String" %>
<%@ attribute name="submenu" required="true" type="java.lang.String" %>

<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="/static/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>${user.username}</p>
                <a href="#"><i class="fa fa-circle text-success"></i>在线</a>
            </div>
        </div>

        <ul class="sidebar-menu" data-widget="tree">
            <li class="header">主导航栏</li>

            <li class="treeview">
                <a href="#">
                    <i class="fa fa-fw fa-shopping-cart"></i>
                    <span>商品管理</span>
                    <span class="pull-right-container">
                              <i class="fa fa-angle-left pull-right"></i>
                            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="#"><i class="fa fa-circle-o"></i>添加商品</a></li>
                    <li><a href="#"><i class="fa fa-circle-o"></i>查看商品列表</a></li>
                </ul>
            </li>

            <li class="treeview ${parentmenu=='用户管理'?  'active' : ''}">
                <a href="#">
                    <i class="fa fa-fw fa-user"></i>
                    <span>用户管理</span>
                    <span class="pull-right-container">
                              <i class="fa fa-angle-left pull-right"></i>
                            </span>
                </a>
                <ul class="treeview-menu">
                    <li class="${submenu=='添加用户'?  'active' : ''}"><a href="/user/form"><i class="fa fa-circle-o"></i>添加用户</a></li>
                    <li class="${submenu=='用户列表'?  'active' : ''}"><a href="/user/list"><i class="fa fa-circle-o"></i>查看用户列表</a></li>
                </ul>
            </li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>