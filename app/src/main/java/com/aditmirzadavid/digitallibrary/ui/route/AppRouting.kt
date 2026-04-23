package com.aditmirzadavid.digitallibrary.ui.route

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.AddToQueue
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.GroupAdd
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aditmirzadavid.digitallibrary.data.container.BookServerContainer
import com.aditmirzadavid.digitallibrary.data.container.LoanServerContainer
import com.aditmirzadavid.digitallibrary.data.container.StudentServerContainer
import com.aditmirzadavid.digitallibrary.ui.view.AddBookView
import com.aditmirzadavid.digitallibrary.ui.view.AddLoanView
import com.aditmirzadavid.digitallibrary.ui.view.AddStudentView
import com.aditmirzadavid.digitallibrary.ui.view.BookDetailView
import com.aditmirzadavid.digitallibrary.ui.view.BookListView
import com.aditmirzadavid.digitallibrary.ui.view.LoanListView
import com.aditmirzadavid.digitallibrary.ui.view.StudentListView
import com.aditmirzadavid.digitallibrary.ui.viewmodel.AddBookViewModel
import com.aditmirzadavid.digitallibrary.ui.viewmodel.AddLoanViewModel
import com.aditmirzadavid.digitallibrary.ui.viewmodel.AddStudentViewModel
import com.aditmirzadavid.digitallibrary.ui.viewmodel.BookDetailViewModel
import com.aditmirzadavid.digitallibrary.ui.viewmodel.BookListViewModel
import com.aditmirzadavid.digitallibrary.ui.viewmodel.LoanListViewModel
import com.aditmirzadavid.digitallibrary.ui.viewmodel.StudentListViewModel

/* ===================== PILIHAN VIEW ===================== */

enum class AppView(val title: String, val icon: ImageVector? = null) {
    ListBook("List Book", Icons.Filled.Collections),
    BookDetail("Book Detail", Icons.Filled.Book),
    AddBook("Add Book", Icons.Filled.AddBox),
    ListLoan("List Loan", Icons.Filled.BookmarkAdd),
    AddLoan("Add Loan", Icons.Filled.AddToQueue),
    ListStudent("List Student", Icons.Filled.People),
    AddStudent("Add Student", Icons.Filled.GroupAdd)
}

/* ===================== BOTTOM NAV ===================== */

data class BottomNavItem(val view: AppView, val label: String)

/* ===================== TOP BAR ===================== */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    currentView: AppView?,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit
) {
    TopAppBar(
        title = {
            Text(currentView?.title ?: "App")
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}

/* ===================== BOTTOM NAV ===================== */

@Composable
fun MyBottomNavigationBar(
    navController: NavHostController,
    currentDestination: NavDestination?,
    items: List<BottomNavItem>
) {
    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    item.view.icon?.let {
                        Icon(it, contentDescription = item.label)
                    }
                },
                label = { Text(item.label) },
                selected = currentDestination?.route
                    ?.substringBefore("/") == item.view.name,
                onClick = {
                    navController.navigate(item.view.name) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

/* ===================== MAIN ROUTING ===================== */

@Composable
fun AppRouting() {

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination


    // Mengambil route (halaman) saat ini dari navigation.
    // currentDestination?.route berisi route lengkap,
    val currentRoute = currentDestination?.route?.substringBefore("/")

    // Menentukan halaman (view) yang sedang aktif berdasarkan route saat ini.
    // AppView.values() digunakan untuk mengambil seluruh daftar enum AppView.
    val currentView = AppView.values().find { it.name == currentRoute }

    // Daftar yang digunakan oleh composable BottomNavigationBar
    // untuk menampilkan menu navigasi di bagian bawah aplikasi.
    val bottomNavItems = listOf(
        BottomNavItem(AppView.ListBook, "Book"),
        BottomNavItem(AppView.ListLoan, "Loan"),
        BottomNavItem(AppView.ListStudent, "Student")
    )

    Scaffold(
        topBar = {
            MyTopAppBar(
                currentView = currentView,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        },
        bottomBar = {
            MyBottomNavigationBar(
                navController = navController,
                currentDestination = currentDestination,
                items = bottomNavItems
            )
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = AppView.ListBook.name,
            modifier = Modifier.padding(padding)
        ) {

            /* ===================== BOOK ===================== */

            composable(AppView.ListBook.name) {
                val viewModel = remember {
                    BookListViewModel(BookServerContainer.bookRepository)
                }
                BookListView(
                    navController = navController,
                    viewModel = viewModel
                )
            }

            composable("${AppView.BookDetail.name}/{idbuku}") { backStack ->
                val idbuku = backStack.arguments?.getString("idbuku") ?: ""

                val viewModel = remember {
                    BookDetailViewModel(BookServerContainer.bookRepository)
                }

                BookDetailView(idbuku, viewModel)
            }

            composable(AppView.AddBook.name) {
                val viewModel = remember {
                    AddBookViewModel(BookServerContainer.bookRepository)
                }
                AddBookView(viewModel)
            }

            /* ===================== LOAN ===================== */

            composable(AppView.ListLoan.name) {
                val viewModel = remember {
                    LoanListViewModel(LoanServerContainer.loanRepository)
                }
                LoanListView(navController, viewModel)
            }

            composable(AppView.AddLoan.name) {
                val viewModel = remember {
                    AddLoanViewModel(LoanServerContainer.loanRepository)
                }
                AddLoanView(viewModel)
            }

            /* ===================== STUDENT ===================== */

            composable(AppView.ListStudent.name) {
                val viewModel = remember {
                    StudentListViewModel(StudentServerContainer.studentRepository)
                }
                StudentListView(navController, viewModel)
            }

            composable(AppView.AddStudent.name) {
                val viewModel = remember {
                    AddStudentViewModel(StudentServerContainer.studentRepository)
                }
                AddStudentView(viewModel)
            }
        }
    }
}