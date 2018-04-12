using IntervalDisplayLibrary.Components;
using IntervalDisplayLibrary.Composition;
using IntervalDisplayLibrary.Compositors;
using Model.ValueTypes;
using System;
using System.Drawing;
using System.Windows.Forms;

namespace WindowsFormsApp1
{
    public partial class FormMain : Form
    {
        public FormMain()
        {
            InitializeComponent();           
        }

        private void Form1_Resize(object sender, EventArgs e)
        {
            Init();
        }

        private void Form1_Paint(object sender, PaintEventArgs e)
        {
            Init();
        }

        private void Init()
        {
            //var compositeRoot = new Composite("composite1");

            //compositeRoot.Add(new ComponentContactGroup("CG_C", null));
            //compositeRoot.Add(new ComponentAbsence("Abs1", null));
            //compositeRoot.Add(new ComponentContactGroup("CG_A", null));
            //compositeRoot.Add(new ComponentAbsence("Abs2", null));
            //compositeRoot.Add(new ComponentContactGroup("CG_B", null));

            //Composite composite2 = new Composite("composite2");
            //composite2.Add(new ComponentInterval("Interval", null));

            //composite2.Add(new ComponentContactGroup("CG_A", null));
            //composite2.Add(new ComponentAbsence("Abs2", null));
            //composite2.Add(new ComponentContactGroup("CG_B", null));

            //compositeRoot.Add(composite2);


            CompositorSeparateLine compositor = new CompositorSeparateLine(this.Width);
            Composition composition = new Composition(compositor);

            composition.Add(new ComponentText("TEXT_1"));

            composition.Add(new ComponentContactGroup("CG_A", new IntervalType(DateTime.Now.AddDays(-10), DateTime.Now.AddDays(10), "TEST_COMMENT")));
            composition.Add(new ComponentAbsence("Abs1", new IntervalType(DateTime.Now.AddDays(-13), DateTime.Now.AddDays(8), "TEST_COMMENT")));
            composition.Add(new ComponentContactGroup("CG_A1", new IntervalType(DateTime.Now.AddDays(-130), DateTime.Now.AddDays(80), "TEST_COMMENT")));
            composition.Add(new ComponentAbsence("Abs2", new IntervalType(DateTime.Now.AddDays(-10), DateTime.Now.AddDays(10), "TEST_COMMENT")));
            composition.Add(new ComponentContactGroup("CG_B", new IntervalType(DateTime.Now.AddDays(-10), DateTime.Now.AddDays(10), "TEST_COMMENT")));
                        
            composition.Add(new ComponentInterval("Interval", new IntervalType(DateTime.Now.AddDays(-12), DateTime.Now.AddDays(120), "TEST_COMMENT")));
                        
            composition.Add(new ComponentContactGroup("CG_A2", new IntervalType(DateTime.Now.AddDays(-12), DateTime.Now.AddDays(120), "TEST_COMMENT")));
            composition.Add(new ComponentAbsence("Abs2", new IntervalType(DateTime.Now.AddDays(-10), DateTime.Now.AddDays(10), "TEST_COMMENT")));
            composition.Add(new ComponentContactGroup("CG_C", new IntervalType(DateTime.Now.AddDays(-10), DateTime.Now.AddDays(10), "TEST_COMMENT")));
            

            Graphics formGraphics = this.CreateGraphics();
            formGraphics.Clear(this.BackColor);

            composition.Draw(formGraphics);

            formGraphics.Dispose();
        }

    }
}
