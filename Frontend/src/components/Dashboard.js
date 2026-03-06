import React, { useEffect, useState } from "react";
import axios from "axios";
import { Pie } from "react-chartjs-2";
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js";

ChartJS.register(ArcElement, Tooltip, Legend);

function Dashboard() {

  const [section, setSection] = useState("dashboard");

  const [summary, setSummary] = useState({
    totalIncome: 0,
    totalExpense: 0,
    balance: 0
  });

  const [expenses, setExpenses] = useState([]);
  const [filteredExpenses, setFilteredExpenses] = useState([]);

  const [categoryFilter, setCategoryFilter] = useState("");

  const [form, setForm] = useState({
    description: "",
    amount: "",
    category: "",
    date: "",
    type: ""
  });

  useEffect(() => {
    loadDashboard();
    loadExpenses();
  }, []);

  const loadDashboard = () => {
    axios.get("http://localhost:8080/api/expenses/dashboard")
      .then(res => setSummary(res.data))
      .catch(err => console.log(err));
  };

  const loadExpenses = () => {
    axios.get("http://localhost:8080/api/expenses")
      .then(res => setExpenses(res.data))
      .catch(err => console.log(err));
  };

  const filterByCategory = () => {

    if (!categoryFilter) return;

    axios
      .get(`http://localhost:8080/api/expenses/category/${categoryFilter}`)
      .then(res => setFilteredExpenses(res.data))
      .catch(err => console.log(err));
  };

  const deleteExpense = (id) => {

    axios.delete(`http://localhost:8080/api/expenses/${id}`)
      .then(() => {
        loadExpenses();
        loadDashboard();
        filterByCategory();
      })
      .catch(err => console.log(err));
  };

  const addTransaction = () => {

    if (!form.description || !form.amount || !form.category || !form.type) {
      alert("Please fill all fields");
      return;
    }

    axios.post("http://localhost:8080/api/expenses", {
      description: form.description,
      amount: Number(form.amount),
      date: form.date,
      category: { categoryId: Number(form.category) },
      type: { typeId: Number(form.type) }
    })
    .then(() => {

      setForm({
        description: "",
        amount: "",
        category: "",
        date: "",
        type: ""
      });

      loadExpenses();
      loadDashboard();
    })
    .catch(err => {
      console.error(err);
      alert("Error adding transaction");
    });
  };

  const chartData = {
    labels: ["Income", "Expense"],
    datasets: [
      {
        data: [summary.totalIncome, summary.totalExpense],
        backgroundColor: ["#4CAF50", "#F44336"]
      }
    ]
  };

  return (
    <div style={{ fontFamily: "Arial" }}>

      {/* NAVIGATION BAR */}

      <div
        style={{
          background: "#1f1f2e",
          display: "flex",
          justifyContent: "space-evenly",
          padding: "15px 0"
        }}
      >

        <button style={navButton} onClick={() => setSection("dashboard")}>
          Dashboard
        </button>

        <button style={navButton} onClick={() => setSection("add")}>
          Add Expense
        </button>

        <button style={navButton} onClick={() => setSection("view")}>
          View Expenses
        </button>

        <button style={navButton} onClick={() => setSection("category")}>
          View By Category
        </button>

      </div>

      {/* MAIN CONTENT */}

      <div
        style={{
          padding: "40px",
          display: "flex",
          flexDirection: "column",
          alignItems: "center"
        }}
      >

        <h2>Expense Tracker Dashboard</h2>

        {/* DASHBOARD */}

        {section === "dashboard" && (
          <>
            <div style={{ display: "flex", gap: "30px" }}>

              <div style={cardStyle}>
                <h3>Total Income</h3>
                <h2>₹ {summary.totalIncome}</h2>
              </div>

              <div style={cardStyle}>
                <h3>Total Expense</h3>
                <h2>₹ {summary.totalExpense}</h2>
              </div>

              <div style={cardStyle}>
                <h3>Balance</h3>
                <h2>₹ {summary.balance}</h2>
              </div>

            </div>

            <h3 style={{ marginTop: "40px" }}>Analytics</h3>

            <div style={{ width: "300px" }}>
              <Pie data={chartData} />
            </div>
          </>
        )}

        {/* ADD EXPENSE */}

        {section === "add" && (

          <div style={{ textAlign: "center", width: "600px" }}>

            <h3>Add Expense</h3>

            <input style={inputStyle}
              placeholder="Description"
              value={form.description}
              onChange={(e) =>
                setForm({ ...form, description: e.target.value })
              }
            />

            <input style={inputStyle}
              type="number"
              placeholder="Amount"
              value={form.amount}
              onChange={(e) =>
                setForm({ ...form, amount: e.target.value })
              }
            />

            <select style={inputStyle}
              value={form.category}
              onChange={(e) =>
                setForm({ ...form, category: e.target.value })
              }
            >
              <option value="">Select Category</option>
              <option value="1">Food</option>
              <option value="2">Travel</option>
              <option value="3">Shopping</option>
            </select>

            <input style={inputStyle}
              type="date"
              value={form.date}
              onChange={(e) =>
                setForm({ ...form, date: e.target.value })
              }
            />

            <select style={inputStyle}
              value={form.type}
              onChange={(e) =>
                setForm({ ...form, type: e.target.value })
              }
            >
              <option value="">Select Type</option>
              <option value="1">INCOME</option>
              <option value="2">EXPENSE</option>
            </select>

            <br />

            <button style={actionButton} onClick={addTransaction}>
              Add
            </button>

          </div>
        )}

        {/* VIEW EXPENSES */}

       {section === "view" && (

  <div style={{ width: "900px", textAlign: "center" }}>

    <h3>All Transactions</h3>

    <table
      style={{
        width: "100%",
        borderCollapse: "collapse",
        marginTop: "20px",
        textAlign: "center"
      }}
    >

      <thead>
        <tr style={{ background: "#f2f2f2" }}>
          <th style={{ padding: "12px", borderBottom: "1px solid #ccc" }}>Description</th>
          <th style={{ padding: "12px", borderBottom: "1px solid #ccc" }}>Amount</th>
          <th style={{ padding: "12px", borderBottom: "1px solid #ccc" }}>Category</th>
          <th style={{ padding: "12px", borderBottom: "1px solid #ccc" }}>Type</th>
          <th style={{ padding: "12px", borderBottom: "1px solid #ccc" }}>Date</th>
          <th style={{ padding: "12px", borderBottom: "1px solid #ccc" }}>Action</th>
        </tr>
      </thead>

      <tbody>

        {expenses.map(e => (

          <tr key={e.id}>

            <td style={{ padding: "10px" }}>{e.description}</td>

            <td style={{ padding: "10px" }}>
              ₹ {e.amount}
            </td>

            <td style={{ padding: "10px" }}>
              {e.category?.categoryName}
            </td>

            <td style={{ padding: "10px" }}>
              {e.type?.typeName}
            </td>

            <td style={{ padding: "10px" }}>
              {e.date}
            </td>

            <td style={{ padding: "10px" }}>
              <button
                onClick={() => deleteExpense(e.id)}
                style={{
                  background: "red",
                  color: "white",
                  border: "none",
                  padding: "6px 12px",
                  cursor: "pointer"
                }}
              >
                Delete
              </button>
            </td>

          </tr>

        ))}

      </tbody>

    </table>

  </div>

)}
        {/* VIEW BY CATEGORY */}

        {section === "category" && (

  <div style={{ textAlign: "center", width: "900px" }}>

    <h3>Filter Expenses</h3>

    <select
      style={{ padding: "8px", marginRight: "10px" }}
      value={categoryFilter}
      onChange={(e) => setCategoryFilter(e.target.value)}
    >
      <option value="">Select Category</option>
      <option value="1">Food</option>
      <option value="2">Travel</option>
      <option value="3">Shopping</option>
    </select>

    <button
      style={{
        padding: "8px 16px",
        background: "#1976d2",
        color: "white",
        border: "none",
        cursor: "pointer",
        borderRadius: "4px"
      }}
      onClick={filterByCategory}
    >
      View By Category
    </button>

    <h3 style={{ marginTop: "30px" }}>Transactions</h3>

    <table
      style={{
        width: "100%",
        borderCollapse: "collapse",
        marginTop: "20px",
        textAlign: "center"
      }}
    >

      <thead>
        <tr style={{ background: "#f2f2f2" }}>
          <th style={{ padding: "12px", borderBottom: "1px solid #ccc" }}>Description</th>
          <th style={{ padding: "12px", borderBottom: "1px solid #ccc" }}>Amount</th>
          <th style={{ padding: "12px", borderBottom: "1px solid #ccc" }}>Category</th>
          <th style={{ padding: "12px", borderBottom: "1px solid #ccc" }}>Type</th>
          <th style={{ padding: "12px", borderBottom: "1px solid #ccc" }}>Date</th>
        </tr>
      </thead>

      <tbody>

        {filteredExpenses.map(e => (

          <tr key={e.id}>

            <td style={{ padding: "10px" }}>{e.description}</td>

            <td style={{ padding: "10px" }}>
              ₹ {e.amount}
            </td>

            <td style={{ padding: "10px" }}>
              {e.category?.categoryName}
            </td>

            <td style={{ padding: "10px" }}>
              {e.type?.typeName}
            </td>

            <td style={{ padding: "10px" }}>
              {e.date}
            </td>

          </tr>

        ))}

      </tbody>

    </table>

  </div>

)}
      </div>

    </div>
  );
}

/* STYLES */

const navButton = {
  background: "transparent",
  color: "white",
  border: "none",
  fontSize: "16px",
  cursor: "pointer"
};

const cardStyle = {
  padding: "20px",
  borderRadius: "10px",
  background: "#f2f2f2",
  minWidth: "150px",
  textAlign: "center"
};

const inputStyle = {
  margin: "8px",
  padding: "6px",
  width: "160px"
};

const actionButton = {
  padding: "6px 15px",
  cursor: "pointer",
  background: "#1976d2",
  color: "white",
  border: "none",
  borderRadius: "4px"
};

const deleteButton = {
  background: "red",
  color: "white",
  border: "none",
  padding: "5px 10px",
  cursor: "pointer"
};

const tableStyle = {
  width: "100%",
  borderCollapse: "collapse"
};

export default Dashboard;