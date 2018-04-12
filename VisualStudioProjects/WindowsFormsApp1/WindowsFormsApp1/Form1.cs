using CompositeClassLibrary;
using CompositeClassLibrary.CompositePattern;
using CompositeClassLibrary.CompositorPattern;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using Model.ValueTypes;
using System.Windows.Forms;

namespace WindowsFormsApp1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();           
        }

        private void button1_Click(object sender, EventArgs e)
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

            Graphics formGraphics = this.CreateGraphics();
            formGraphics.Clear(this.BackColor);


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

            //composition.Add(compositeRoot);

            //composition.Compositor_Compose(this.Width);

            composition.Draw(formGraphics);

            formGraphics.Dispose();
        }

        private void Form1_Resize(object sender, EventArgs e)
        {
            button1_Click(sender, e);
        }
    }
}
